import pygame
import os.path

# no va la musica, per aixo coment
# class Sound:
#    def __init__(self):
#        pygame.mixer.init()
#        # asignar volumen: pygame.mixer.music.set_volume(0.4)
#        pygame.mixer.music.load(os.path.join("src", "Apoxode_-_Electric_1.mp3"))
#        # pygame.mixer.music.play(loops=-1)
#        self.move_up_sound = pygame.mixer.Sound(os.path.join("src", "Rising_putter.ogg"))
#        self.move_down_sound = pygame.mixer.Sound(os.path.join("src", "Falling_putter.ogg"))
#        self.collision_sound = pygame.mixer.Sound(os.path.join("src", "Collision.ogg"))
#
#    def play_music(self, loops=-1):
#        pygame.mixer.music.play(loops)
#
#    def stop_music(self):
#        pygame.mixer.music.stop()
#
#    def play_move_up_sound(self):
#        self.move_up_sound.play()
#
#    def play_move_down_sound(self):
#        self.move_down_sound.play()
#
#    def play_collision_sound(self):
#        self.collision_sound.play()
#
#    def quit(self):
#        pygame.mixer.quit()
#