import pygame
import random
import os.path
from Tamany import *

class Enemy(pygame.sprite.Sprite):
    def __init__(self):
        super(Enemy, self).__init__()
        self.surf = pygame.image.load(os.path.join("src","missile.png")).convert()
        self.surf.set_colorkey((255, 255, 255), pygame.RLEACCEL)
        self.rect = self.surf.get_rect(
            center=(
                random.randint(SCREEN_WIDTH + 20, SCREEN_WIDTH + 100),
                random.randint(0, SCREEN_HEIGHT),
            )
        )
        self.speed = random.randint(EVIL_PLAYER_SPEED_MIN, EVIL_PLAYER_SPEED_MAX)
        self.score_added = False
        
    def update(self):
        # to points
        global SCORE
        global LEVEL
        self.rect.move_ip(-self.speed, 0)
        if self.rect.right < 0:
            if not self.score_added:
                SCORE[0] += 10
            self.kill()
            if SCORE[0] >= 500:
                LEVEL[0] += 1
                SCORE[0] = 0
                

