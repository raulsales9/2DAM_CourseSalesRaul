import pygame
import random

# Cloud part
class Cloud(pygame.sprite.Sprite):
    def __init__(self, SCREEN_WIDTH, SCREEN_HEIGHT):
        super(Cloud, self).__init__()
        # importem la image base 
        self.surf = pygame.image.load("src/cloud.png").convert()
        self.surf.set_colorkey((0,0,0), pygame.RLEACCEL)
        self.rect = self.surf.get_rect(
            center=(
                random.randint(SCREEN_WIDTH + 20, SCREEN_WIDTH + 100),
                random.randint(0, SCREEN_HEIGHT),
            )
        )
        # intanciem 5 fotogrames requerits al enunciat
        self.speed = 5
            
    def update(self, SCREEN_WIDTH, SCREEN_HEIGHT):
        self.rect.move_ip(-self.speed, 0)
        if self.rect.right < 0:
            self.rect.left = random.randint(SCREEN_WIDTH + 20, SCREEN_WIDTH + 100)
            self.rect.top = random.randint(0, SCREEN_HEIGHT)
            