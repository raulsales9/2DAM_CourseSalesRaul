import pygame
import os.path
import os
from Tamany import *

class cohet_defensa(pygame.sprite.Sprite):
    def __init__(self, rect) -> None:
        super().__init__()
        self.surf = pygame.image.load(os.path.join("src","pngwing.png")).convert()
        self.surf.set_colorkey((255,255,255), RLEACCEL)
        self.surf = pygame.transform.scale(self.surf, (20, 20)).convert()
        self.timer = 5
        self.rect = rect
        self.speed = S400_SPEED
        self.score_added = False
        
    def update(self):
        global SCORE
        global LEVEL
        self.rect.move_ip(self.speed, 0)
        if self.rect.left > SCREEN_WIDTH:
            if not self.score_added:
                SCORE[0] -= 1
                self.score_added = True
            self.kill()