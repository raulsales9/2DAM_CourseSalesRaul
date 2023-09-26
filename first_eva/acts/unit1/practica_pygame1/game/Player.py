import pygame
import os.path
from Tamany import *
from pygame.locals import K_UP, K_DOWN, K_LEFT, K_RIGHT

# from Sound import Sound

class Player(pygame.sprite.Sprite):
    move_down_sound = None
    move_up_sound = None
    def __init__(self, move_up_sound, move_down_sound, ):
        super(Player, self).__init__()
        self.move_up_sound = move_up_sound
        self.move_down_sound = move_down_sound
        self.surf = pygame.image.load(os.path.join("src","jet.png")).convert()
        self.surf.set_colorkey((255, 255, 255), RLEACCEL)
        self.rect = self.surf.get_rect()
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
