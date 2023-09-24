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
import random
from Enemy import Enemy
from Player import Player

# Configuración de la pantalla
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600

# Inicializa Pygame
pygame.init()

# Crea la pantalla
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
ADDENEMY = pygame.USEREVENT + 1
pygame.time.set_timer(ADDENEMY, 250)

# Crea el jugador
player = Player()

# Grupos de sprites
enemies = pygame.sprite.Group()
all_sprites = pygame.sprite.Group()
all_sprites.add(player)

# Configura el reloj
clock = pygame.time.Clock()

running = True

while running:
    for event in pygame.event.get():
        if event.type == KEYDOWN:
            if event.key == K_ESCAPE:
                running = False
        elif event.type == QUIT:
            running = False

    # Genera nuevos enemigos
    if random.randint(1, 100) < 10:
        new_enemy = Enemy(SCREEN_WIDTH, SCREEN_HEIGHT)
        enemies.add(new_enemy)
        all_sprites.add(new_enemy)

    keys = pygame.key.get_pressed()
    player.update(keys)
    enemies.update()

    # Dibuja los sprites en la pantalla
    screen.fill((255, 255, 255))
    for entity in all_sprites:
        screen.blit(entity.surf, entity.rect)

    # Verifica la colisión entre el jugador y los enemigos
    if pygame.sprite.spritecollide(player, enemies, False):
        player.kill()
        running = False  # Termina el juego en caso de colisión

    pygame.display.flip()

    # Controla la velocidad del juego
    clock.tick(30)

pygame.quit()  # Cierra Pygame al finalizar
