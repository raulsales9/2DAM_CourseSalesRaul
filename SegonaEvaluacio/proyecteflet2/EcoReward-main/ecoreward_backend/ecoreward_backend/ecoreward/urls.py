from django.urls import path
from .views import signup, signin, api_list_users,api_list_hcompras

urlpatterns = [
    path('signup/', signup, name="register_user"),
    path('signin/', signin, name="login_user"),
    path('apiuserlist/', api_list_users, name="listusers"),
    path('api/hcompras/<int:user_id>/', api_list_hcompras, name='api_list_hcompras'),
]