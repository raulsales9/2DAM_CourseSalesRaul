import pygame
import os.path
from pygame.locals import K_UP, K_DOWN, K_LEFT, K_RIGHT, KEYDOWN
from Tamany import *
class Player(pygame.sprite.Sprite):
    def __init__(self, move_up_sound, move_down_sound):
        super(Player, self).__init__()
        self.move_up_sound = move_up_sound
        self.move_down_sound = move_down_sound
        image_path = os.path.join(os.path.dirname(__file__), "src", "jet.png")
        self.surf = pygame.image.load(image_path).convert()
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
            
    # def reset_position(self):
    #    self.rect.left = SCREEN_WIDTH // 4
    #    self.rect.centery = SCREEN_HEIGHT // 2



    