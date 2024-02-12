from django.db import models
from django.contrib.auth.models import AbstractBaseUser, BaseUserManager
from django.db import models


class Acciones(models.Model):
    fecha = models.DateField(blank=True, null=True)
    iduser = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'acciones'


class AuthGroup(models.Model):
    name = models.CharField(unique=True, max_length=150)

    class Meta:
        managed = False
        db_table = 'auth_group'


class AuthGroupPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)
    permission = models.ForeignKey('AuthPermission', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'
        unique_together = (('group', 'permission'),)


class AuthPermission(models.Model):
    name = models.CharField(max_length=255)
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING)
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'
        unique_together = (('content_type', 'codename'),)


class AuthUser(models.Model):
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.BooleanField()
    username = models.CharField(unique=True, max_length=150)
    first_name = models.CharField(max_length=150)
    last_name = models.CharField(max_length=150)
    email = models.CharField(max_length=254)
    is_staff = models.BooleanField()
    is_active = models.BooleanField()
    date_joined = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'auth_user'


class AuthUserGroups(models.Model):
    id = models.BigAutoField(primary_key=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_groups'
        unique_together = (('user', 'group'),)


class AuthUserUserPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    permission = models.ForeignKey(AuthPermission, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_user_permissions'
        unique_together = (('user', 'permission'),)


class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.SmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)


class DjangoMigrations(models.Model):
    id = models.BigAutoField(primary_key=True)
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'


class Ecopoints(models.Model):
    totalcoins = models.IntegerField(blank=True, null=True)
    fecha = models.DateField(blank=True, null=True)
    iduser = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'ecopoints'


class Elemento(models.Model):
    name = models.CharField(max_length=255, blank=True, null=True)
    value = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'elemento'


class EmpSostenible(models.Model):
    name = models.CharField(max_length=255, blank=True, null=True)
    value = models.IntegerField(blank=True, null=True)
    descripcion = models.CharField(max_length=255, blank=True, null=True)
    coins = models.IntegerField(blank=True, null=True)
    iduser = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'emp_sostenible'


class HAcciones(models.Model):
    id = models.AutoField(primary_key=True)
    fecha = models.DateField(blank=True, null=True)
    iduser = models.IntegerField(blank=True, null=True)
    t_accion = models.CharField(max_length=255, blank=True, null=True)
    value = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'h_acciones'


class HCompras(models.Model):
    id = models.AutoField(primary_key=True)
    fecha = models.DateField(blank=True, null=True)
    iduser = models.IntegerField(blank=True, null=True)
    producto = models.CharField(max_length=255, blank=True, null=True)
    c_total = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'h_compras'


class Logros(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=255, blank=True, null=True)
    value = models.IntegerField(blank=True, null=True)
    iduser = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'logros'


class Message(models.Model):
    receiver = models.IntegerField(blank=True, null=True)
    emitter = models.IntegerField(blank=True, null=True)
    name = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'message'


class ProdRecompensa(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=255, blank=True, null=True)
    value = models.IntegerField(blank=True, null=True)
    iduser = models.IntegerField(blank=True, null=True)
    idemp = models.ForeignKey(EmpSostenible, models.DO_NOTHING, db_column='idemp', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'prod_recompensa'


class ProdSostenible(models.Model):
    id = models.AutoField(primary_key=True)
    fecha = models.DateField(blank=True, null=True)
    iduser = models.IntegerField(blank=True, null=True)
    descripcion = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'prod_sostenible'


class Reciclaje(models.Model):
    descripcion = models.CharField(max_length=255, blank=True, null=True)
    value = models.IntegerField(blank=True, null=True)
    t_reciclaje = models.CharField(max_length=255, blank=True, null=True)
    iduser = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'reciclaje'


class TransporteEcologico(models.Model):
    id = models.AutoField(primary_key=True)
    fecha = models.DateField(blank=True, null=True)
    iduser = models.IntegerField(blank=True, null=True)
    kilometros = models.IntegerField(blank=True, null=True)
    vlue = models.IntegerField(blank=True, null=True)
    t_vehiculo_descripcion = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'transporte_ecologico'


class Usuario(models.Model):
    id = models.AutoField(primary_key=True)
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.BooleanField()
    username = models.CharField(max_length=150)
    first_name = models.CharField(max_length=150)
    last_name = models.CharField(max_length=150)
    email = models.CharField(max_length=254)
    is_staff = models.BooleanField()
    is_active = models.BooleanField()
    date_joined = models.DateTimeField()
    coinstotales = models.BigIntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'usuario'
