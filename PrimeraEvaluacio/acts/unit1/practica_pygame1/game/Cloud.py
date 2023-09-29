import pygame
import random
import os.path
from Tamany import *

class Cloud(pygame.sprite.Sprite):
    def __init__(self):
        super(Cloud, self).__init__()
        # accedim a la imatge per os.path.jpin com a ruta relativa
        self.surf = pygame.image.load(os.path.join("src","cloud.png")).convert()
        self.surf.set_colorkey((0, 0, 0), RLEACCEL)
        self.rect = self.surf.get_rect(
            center = (
                random.randint(SCREEN_WIDTH+20, SCREEN_WIDTH+100),
                random.randint(0, SCREEN_HEIGHT),
            )
        )
        # Hagafem la velocitat que hem definit a Tamany
        self.speed = CLOUD_SPEED

    def update(self):
        self.rect.move_ip(-self.speed, 0)
        if self.rect.right < 0:
            self.kill()
            
            
            