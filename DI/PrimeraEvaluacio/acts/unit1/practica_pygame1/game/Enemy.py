import pygame
import random
import os.path
from Tamany import *

class Enemy(pygame.sprite.Sprite):
    def __init__(self):
        super(Enemy, self).__init__()
        image_path = os.path.join(os.path.dirname(__file__), "src", "missile.png")
        self.surf = pygame.image.load(image_path).convert()
        self.surf = pygame.transform.scale(self.surf, (20, 20)).convert()
        self.surf.set_colorkey((255, 255, 255), RLEACCEL)
        self.rect = self.surf.get_rect(
            center=(
                random.randint(SCREEN_WIDTH + 20, SCREEN_WIDTH + 100),
                random.randint(0, SCREEN_HEIGHT),
            )
        )
        # velocitat minima i maxima  aleatoria 
        self.speed = random.randint(EVIL_PLAYER_SPEED_MIN, EVIL_PLAYER_SPEED_MAX)
        self.score_added = False
        
    def update(self):
        # per als punts i nivells treballem amb les globals
        global SCORE
        global LEVEL
        self.rect.move_ip(-self.speed, 0)
        if self.rect.right < 0:
            
            if not self.score_added:
                SCORE[0] += 10
            # cuan arriba al fons que es destroixca, i no sobrecarregue la ram
            self.kill()
            #logica per a incrementar el nivell al arribar a 500 punts 
            if SCORE[0] >= 500:
                LEVEL[0] += 1
                # reinciniem l'score
                SCORE[0] = 0
                

