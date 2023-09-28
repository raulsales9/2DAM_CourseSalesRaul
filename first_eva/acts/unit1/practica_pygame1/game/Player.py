import pygame
from pygame.locals import K_UP, K_DOWN, K_LEFT, K_RIGHT, KEYDOWN
from Tamany import *
from cohet_defensa import cohet_defensa
class Player(pygame.sprite.Sprite):
    def __init__(self, move_up_sound, move_down_sound, missiles_group):
        super(Player, self).__init__()
        self.move_up_sound = move_up_sound
        self.move_down_sound = move_down_sound
        self.missiles_group = missiles_group  # Agregamos el grupo de misiles
        self.surf = pygame.image.load(os.path.join("src", "jet.png")).convert()
        self.surf.set_colorkey((255, 255, 255), pygame.RLEACCEL)
        self.rect = self.surf.get_rect(
            center=(
                100,
                SCREEN_HEIGHT / 2
            )
        )
        self.SCREEN_WIDTH = SCREEN_WIDTH
        self.SCREEN_HEIGHT = SCREEN_HEIGHT

    def update(self, keys):
        if keys[K_UP]:
            self.rect.move_ip(0, -5)
            self.move_up_sound.play()
        if keys[K_DOWN]:
            self.rect.move_ip(0, 5)
            self.move_down_sound.play()
        if keys[K_LEFT]:
            self.rect.move_ip(-5, 0)
        if keys[K_RIGHT]:
            self.rect.move_ip(5, 0)

        if self.rect.left < 0:
            self.rect.left = 0
        if self.rect.right > self.SCREEN_WIDTH:
            self.rect.right = self.SCREEN_WIDTH
        if self.rect.top <= 0:
            self.rect.top = 0
        if self.rect.bottom > self.SCREEN_HEIGHT:
            self.rect.bottom = self.SCREEN_HEIGHT

        # Disparar un misil cuando se presiona la barra espaciadora
        if keys[KEYDOWN]:
            if keys[pygame.K_SPACE]:
                self.shoot()

    def shoot(self):
        new_missile = cohet_defensa(self.rect.right, self.rect.centery)  # Creamos un nuevo misil
        self.missiles_group.add(new_missile)  # Lo agregamos al grupo de misiles
