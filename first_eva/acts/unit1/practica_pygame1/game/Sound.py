import pygame

class Sound:
    def __init__(self):
        # Iniciar el mixer
        pygame.mixer.init()
        
        # Volumen de la música al 90%
        pygame.mixer.music.set_volume(0.4)
        
        # Cargar la música
        pygame.mixer.music.load("src/Apoxode_-_Electric_1.mp3")
        
        # Cargar sonidos adicionales
        self.move_up_Sound = pygame.mixer.Sound("src/Rising_putter.ogg")
        self.move_down_Sound = pygame.mixer.Sound("src/Falling_putter.ogg")
        self.collision_Sound = pygame.mixer.Sound("src/Collision.ogg")

    def play_music(self, loop=-1):
        pygame.mixer.music.play(loop)

    def stop_music(self):
        pygame.mixer.music.stop()

    def play_move_up_Sound(self):
        self.move_up_Sound.play()

    def play_move_down_Sound(self):
        self.move_down_Sound.play()

    def play_collision_sound(self):
        self.collision_Sound.play()

    def quit(self):
        pygame.mixer.quit()
