import pygame

class sound: 
    def __init__(self):
        # start mixer
        pygame.mixer.init()
        
        # the volume of the music is 0.9
        pygame.mixer.music.set_volume(0.9)
        
        # sounds
        self.music = pygame.mixer.music.load("src/Apoxode_-_Electric_1.mp3")
        self.move_up_sound  = pygame.mixer.Sound("src/Rising_putter.ogg")
        self.move_down_sound = pygame.mixer.Sound("src/Falling_putter.ogg")
        self.collision_sound = pygame.mixer.Sound("src/Collision.ogg")

        def play_sound(self, loop=-1):
            pygame.mixer.music.play(loop)
            
        def stop_sound(self):
            pygame.mixer.music.stop()
            
        