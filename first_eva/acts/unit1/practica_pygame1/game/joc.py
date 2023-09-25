# Import the pygame module
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

from cloud import cloud
from player import player
from enemy import enemy

SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600

pygame.mixer.init()            
pygame.init()

# The sounds
pygame.mixer.music.load("src/Apoxode_-_Electric_1.mp3")
move_up_sound = pygame.mixer.Sound("src/Rising_putter.ogg")
move_down_sound = pygame.mixer.Sound("src/Falling_putter.ogg")
collision_sound = pygame.mixer.Sound("src/Collision.ogg")

# the screen
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))

pygame.mixer.music.set_volume(0.5)  # Ajusta el volumen si es necesario
pygame.mixer.music.play(-1)  # Reproduce la música en bucle infinito

# new enemy
ADDENEMY = pygame.USEREVENT + 1
pygame.time.set_timer(ADDENEMY, 250)

player = player(SCREEN_WIDTH, SCREEN_HEIGHT)

enemies = pygame.sprite.Group()
all_sprites = pygame.sprite.Group()
all_sprites.add(player)

# Agrega nubes como imágenes de fondo
clouds = pygame.sprite.Group()
cloud_instance = cloud(SCREEN_WIDTH, SCREEN_HEIGHT)  # Usa el nombre de la clase (Cloud) al crear una instancia
all_sprites.add(cloud_instance)
clouds.add(cloud_instance)

clock = pygame.time.Clock()

running = True

# main loop
while running:
    for event in pygame.event.get():
        if event.type == KEYDOWN:
            if event.key == K_ESCAPE:
                running = False
        elif event.type == QUIT:
            running = False

        elif event.type == ADDENEMY:
            new_enemy = enemy(SCREEN_WIDTH, SCREEN_HEIGHT)
            enemies.add(new_enemy)
            all_sprites.add(new_enemy)

    keys = pygame.key.get_pressed()
    player.update(keys)
    enemies.update()
    
    
    screen.fill((0, 0, 0))
    
    for entity in all_sprites:
        screen.blit(entity.surf, entity.rect)
        
    if pygame.sprite.spritecollideany(player, enemies):
        player.kill()
        pygame.mixer.music.stop()
        collision_sound.play()
        # aplciamos retraso para escuchar el sonido de colision
        pygame.time.delay(1500)
        running = False

    pygame.display.flip()
    clock.tick(30)
    
pygame.mixer.quit()
pygame.quit()
