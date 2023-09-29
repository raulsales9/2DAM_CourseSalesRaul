import pygame
import os.path
import os
from Tamany import *

class Impacte(pygame.sprite.Sprite):
    def __init__(self, rect):
        super(Impacte, self).__init__()
        self.surf = pygame.image.load(os.path.join("src", "explosion2.jpg"))
        self.surf = pygame.transform.scale(self.surf, (64, 64))
        self.rect = rect
        self.timer = 5 
        self.speed = S400_SPEED

    def process(self):
        if self.timer < 0:
            self.kill()

    def update_timer(self):
        self.timer = self.timer - 1

    # def update(self):
    #    self.rect.move_ip(-self.speed, 0)