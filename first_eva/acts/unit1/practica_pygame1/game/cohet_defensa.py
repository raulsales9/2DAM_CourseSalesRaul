import pygame
import os.path
import os
from Tamany import *

class cohet_defensa(pygame.sprite.Sprite):
    def __init__(self, rect) -> None:
        super().__init__()
        self.surf = pygame.image.load(os.path.join("src","s400.png")).convert()
        self.surf.set_colorkey((255,255,255), RLEACCEL)
        self.timer = 5
        self.rect = rect
        self.speed = S400_SPEED
        
    def update(self):
        global SCORE
        global LEVEL
        self.rect.move_ip(self.speed, 0)
        if self.rect.left > 0:
            if self.score_added:
                SCORE[0] -= 1
            self.kill()