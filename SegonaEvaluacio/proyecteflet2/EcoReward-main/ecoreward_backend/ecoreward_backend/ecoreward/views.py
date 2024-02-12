import json

import jwt
from django.conf import settings
from django.contrib.auth.models import User
from .models import HCompras, Usuario
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth.decorators import login_required
from django.contrib.auth import authenticate, login


@csrf_exempt
def signup(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            username = data.get('username', '')
            password = data.get('password', '')
            email = data.get('email', '')

            if User.objects.filter(email=email).exists():
                return JsonResponse({'error': 'El correo electrónico ya existe'}, status=400)
            elif User.objects.filter(username=username).exists():
                return JsonResponse({'error': 'El nombre de usuario ya existe'}, status=400)
            else:
                user = User.objects.create_user(username=username, email=email, password=password)
                user.save()
                payload = {
                    'user_id': user.id,
                    'username': user.username
                }
                token = jwt.encode(payload, settings.SECRET_KEY)
                return JsonResponse({'token': token})

        except json.JSONDecodeError:
            return JsonResponse({'error': 'Error al decodificar el JSON'}, status=400)
    else:
        return JsonResponse({'message': 'Esta es la página de registro de usuario.'})


@csrf_exempt
def signin(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            username = data.get('username', '')
            password = data.get('password', '')

            user = authenticate(username=username, password=password)
            if user is not None:
                payload = {
                    'user_id': user.id,
                    'username': user.username
                }
                token = jwt.encode(payload, settings.SECRET_KEY)
                return JsonResponse({'token': token})

            else:
                return JsonResponse({'error': 'Credenciales inválidas'}, status=400)
        except json.JSONDecodeError:
            return JsonResponse({'error': 'Error al decodificar el JSON'}, status=400)
    else:
        return JsonResponse({'error': 'Método de solicitud no permitido'}, status=405)


def api_list_users(request):
        users = User.objects.all()
        users_list = list(
            users.values("username", "password", "email")
        ) 
        return JsonResponse(users_list, safe=False)


@login_required
def api_list_hcompras(request, user_id):
    if request.method == 'GET':
        try:
            # Filtra las compras basándote en el ID del usuario
            hcompras_list = HCompras.objects.filter(iduser=user_id).values()

            return JsonResponse(list(hcompras_list), safe=False)
        except Exception as e:
            return JsonResponse({'error': str(e)}, status=500)
    else:
        return JsonResponse({'error': 'Método de solicitud no permitido'}, status=405)
