import pygame
from pygame.locals import (
    K_UP,
    K_DOWN,
    K_LEFT,
    K_RIGHT,
    K_ESCAPE,
    KEYDOWN,
    QUIT,
)
from Cloud import Cloud
from Player import Player
from Enemy import Enemy
from Sound import Sound

# Tamaño de la pantalla
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600

# Inicializamos pygame y el mixer de sonido
pygame.init()
pygame.mixer.init()

# Creamos una instancia de la clase Sound
sound_manager = Sound()

# Configuramos la pantalla
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))

# Reproducimos la música en bucle infinito
sound_manager.play_music(-1)

# Creamos una instancia del jugador después de inicializar Pygame
player = Player(SCREEN_WIDTH, SCREEN_HEIGHT)

# Creamos un grupo de enemigos
enemies = pygame.sprite.Group()

# Creamos un grupo de sprites que incluye al jugador
all_sprites = pygame.sprite.Group()
all_sprites.add(player)

# Creamos un grupo de nubes como imágenes de fondo
clouds = pygame.sprite.Group()
cloud_instance = Cloud(SCREEN_WIDTH, SCREEN_HEIGHT)
all_sprites.add(cloud_instance)
clouds.add(cloud_instance)

# Configuramos el reloj para controlar la velocidad de actualización
clock = pygame.time.Clock()

# Creamos eventos personalizados para agregar enemigos y nubes
ADDENEMY = pygame.USEREVENT + 1
ADDCLOUD = pygame.USEREVENT + 2
pygame.time.set_timer(ADDENEMY, 250)
pygame.time.set_timer(ADDCLOUD, 1000)  # Agregar una nube cada 1000 ms (1 segundo)

running = True

# Bucle principal del juego
while running:
    for event in pygame.event.get():
        if event.type == KEYDOWN:
            if event.key == K_ESCAPE:
                running = False
        elif event.type == QUIT:
            running = False

        elif event.type == ADDENEMY:
            new_enemy = Enemy(SCREEN_WIDTH, SCREEN_HEIGHT)
            enemies.add(new_enemy)
            all_sprites.add(new_enemy)

        elif event.type == ADDCLOUD:
            new_cloud = Cloud(SCREEN_WIDTH, SCREEN_HEIGHT)
            clouds.add(new_cloud)
            all_sprites.add(new_cloud)

    keys = pygame.key.get_pressed()
    player.update(keys)
    enemies.update()
    clouds.update(SCREEN_WIDTH, SCREEN_HEIGHT)

    screen.fill((135, 206, 250))  # Establecemos el color de fondo a azul claro

    for cloud in clouds:
        screen.blit(cloud.surf, cloud.rect)

    for entity in all_sprites:
        screen.blit(entity.surf, entity.rect)

    if pygame.sprite.spritecollideany(player, enemies):
        player.kill()
        sound_manager.stop_music()
        sound_manager.play_collision_sound()
        # Aplicamos un retraso para escuchar el sonido de colisión
        pygame.time.delay(1500)
        running = False

    pygame.display.flip()
    clock.tick(30)

# Salimos del juego
sound_manager.quit()
pygame.quit()
