import pygame
import os.path
import os
from Tamany import *

class cohet_defensa(pygame.sprite.Sprite):
    def __init__(self, rect) -> None:
        super().__init__()
        image_path = os.path.join(os.path.dirname(__file__), "src", "pngwing.png")
        self.surf = pygame.image.load(image_path).convert()
        self.surf.set_colorkey((255,255,255), RLEACCEL)
        self.surf = pygame.transform.scale(self.surf, (20, 20)).convert()
        # temps d'efecte
        self.timer = 5
        self.rect = rect
        # global de velocitat de cohet
        self.speed = S400_SPEED
        self.score_added = False
        
    def update(self):
        global SCORE
        global LEVEL
        self.rect.move_ip(self.speed, 0)
        if self.rect.left > SCREEN_WIDTH:
            if not self.score_added:
                if SCORE[0] >= 30:
                    # mini nerf per a no abusar de cohets
                    SCORE[0] -= 30
                else:
                    # no abaixe de 0
                    SCORE[0] == 0
                self.score_added = True
            self.kill()