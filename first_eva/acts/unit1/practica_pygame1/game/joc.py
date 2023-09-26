import pygame
import os.path
import time
from pygame.locals import (
    K_UP,
    K_DOWN,
    K_LEFT,
    K_RIGHT,
    K_ESCAPE,
    K_p,
    K_SPACE,
    KEYDOWN,
    QUIT,
)
from Cloud import Cloud
from Player import Player
from Enemy import Enemy
from Sound import Sound
from Tamany import *

class Joc:
    def __init__(self):
        pygame.mixer.init()
        self.clock = pygame.time.Clock()
        pygame.init()

        # utils
        self.screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
        self.font = pygame.font.SysFont("monospace", 20)
        self.font2 = pygame.font.SysFont("monospace", 50)
        self.background_color = LIGHT_MODE
        self.last_bg = pygame.time.get_ticks()
        
        
        self.CHANGEBG = pygame.USEREVENT + 3
        pygame.time.set_timer(self.CHANGEBG, 20000)  

        self.ADDENEMY = pygame.USEREVENT + 1
        pygame.time.set_timer(self.ADDENEMY, 250)

        self.ADDCLOUD = pygame.USEREVENT + 2
        pygame.time.set_timer(self.ADDCLOUD, 1200)

        self.game_over = False

        while not self.game_over:
            SCORE[0] = 0
            LEVEL[0] = 1
            self.main_menu()
            if not self.game_over:
                self.run_game()
            else:
                self.show_defeat_screen()

        pygame.mixer.music.stop()
        pygame.mixer.quit()
        pygame.quit()

    def main_menu(self):
        self.screen.fill(LIGHT_MODE)

        Text = self.font.render("  The f-16 at Ukraine  ", True, RED)
        Text_center = (
            SCREEN_WIDTH / 2 - Text.get_width() // 2,
            SCREEN_HEIGHT / 2 - Text.get_height() // 2
        )

        TextStart = self.font.render("Press SPACE to Start", True, RED)
        TextStart_center = (
            SCREEN_WIDTH / 2 - TextStart.get_width() // 2,
            SCREEN_HEIGHT / 2 + 30
        )

        clouds_Added = pygame.sprite.Group()
        clouds_Added.add(Cloud())
        clouds_Added.add(Cloud())
        clouds_Added.add(Cloud())

        running = True
        while running:
            for event in pygame.event.get():
                if event.type == KEYDOWN:
                    if event.key == K_p:
                        running = False
                    elif event.key == K_p:
                        running = False

                elif event.type == QUIT:
                    self.game_over = True
                    running = False
                elif event.type == self.ADDCLOUD:
                    new_cloud = Cloud()
                    clouds_Added.add(new_cloud)

            clouds_Added.update()
            self.screen.fill(LIGHT_MODE)

            self.screen.blit(Text, Text_center)
            self.screen.blit(TextStart, TextStart_center)

            for entity in clouds_Added:
                self.screen.blit(entity.surf, entity.rect)

            pygame.display.flip()
            self.clock.tick(30)

    def run_game(self):
        global SCORE
        global LEVEL
        player = Player()
        sound_manager = Sound()
        clouds = pygame.sprite.Group()
        enemies = pygame.sprite.Group()
        all_sprites = pygame.sprite.Group()
        all_sprites.add(player)

        running = True
        while running:
            bg_time = pygame.time.get_ticks()
            
            for event in pygame.event.get():
                if event.type == KEYDOWN:
                    if event.key == K_ESCAPE:
                        running = False
                elif event.type == QUIT:
                    self.game_over = True
                    running = False

                elif event.type == self.ADDENEMY:
                    new_enemy = Enemy()
                    enemies.add(new_enemy)
                    all_sprites.add(new_enemy)
                    self.change_background()

                elif event.type == self.ADDCLOUD:
                    new_cloud = Cloud()
                    clouds.add(new_cloud)
                    all_sprites.add(new_cloud)

                elif event.type == self.CHANGEBG:
                    if bg_time - self.last_bg >= 20000:
                        self.change_background()
                        self.last_bg = bg_time

            keys = pygame.key.get_pressed()
            player.update(keys)
            enemies.update()
            clouds.update()

            self.screen.fill(LIGHT_MODE)

            for cloud in clouds:
                self.screen.blit(cloud.surf, cloud.rect)

            for sprite in all_sprites:
                if type[sprite] != Player:
                    self.screen.blit(sprite.surf, sprite.rect)
                    
            self.screen.blit(player.surf, player.rect)

            if pygame.sprite.spritecollideany(player, enemies):
                sound_manager.play_move_down_Sound.stop()
                sound_manager.play_move_up_Sound.stop()
                sound_manager.play_collision_sound()
                pygame.time.delay(1500)
                running = False

            score_text = "SCORE: {}".format(SCORE[0]) + " LEVEL: {}".format(LEVEL[0])
            score_render = self.font.render(score_text, True, DARK_MODE)
            self.screen.blit(score_render, (10, 10))

            if LEVEL[0] == 1 and SCORE[0] >= 500:
                LEVEL[0] += 1
            elif LEVEL[0] == 5 :
                GamePasssed()

            pygame.display.flip()
            self.clock.tick(30)

    def show_defeat_screen(self):
        self.screen.fill(LIGHT_MODE)

        Text = self.font.render("  The f-16 at Ukraine  ", True, RED)
        Text_center = (
            SCREEN_WIDTH / 2 - Text.get_width() // 2,
            SCREEN_HEIGHT / 2 - Text.get_height() // 2
        )

        TextStart = self.font.render("Press SPACE to Restart", True, RED)
        TextStart_center = (
            SCREEN_WIDTH / 2 - TextStart.get_width() // 2,
            SCREEN_HEIGHT / 2 + 30
        )

        clouds_Added = pygame.sprite.Group()
        clouds_Added.add(Cloud())
        clouds_Added.add(Cloud())
        clouds_Added.add(Cloud())

        running = True
        while running:
            for event in pygame.event.get():
                if event.type == KEYDOWN:
                    if event.key == K_ESCAPE:
                        running = False
                    elif event.key == K_SPACE:
                        self.game_over = False
                        running = False
                elif event.type == QUIT:
                    self.game_over = True
                    running = False
                elif event.type == self.ADDCLOUD:
                    new_cloud = Cloud()
                    clouds_Added.add(new_cloud)

            clouds_Added.update()
            self.screen.fill(LIGHT_MODE)

            self.screen.blit(Text, Text_center)
            self.screen.blit(TextStart, TextStart_center)

            for entity in clouds_Added:
                self.screen.blit(entity.surf, entity.rect)

            pygame.display.flip()
            self.clock.tick(30)

    def change_background(self):
        if self.background_color == LIGHT_MODE:
            self.background_color = DARK_MODE
        else:
            self.background_color = LIGHT_MODE

        self.screen.fill(self.background_color)
        
    # def record(self):
    def gamePassed(self):
        self.screen.fill(LIGHT_MODE)

        Text = self.font.render("  The f-16 at Ukraine has winned ", True, RED)
        Text_center = (
            SCREEN_WIDTH / 2 - Text.get_width() // 2,
            SCREEN_HEIGHT / 2 - Text.get_height() // 2
        )

        TextStart = self.font.render("Press SPACE to Restart", True, RED)
        TextStart_center = (
            SCREEN_WIDTH / 2 - TextStart.get_width() // 2,
            SCREEN_HEIGHT / 2 + 30
        )

        clouds_Added = pygame.sprite.Group()
        clouds_Added.add(Cloud())
        clouds_Added.add(Cloud())
        clouds_Added.add(Cloud())

        running = True
        while running:
            for event in pygame.event.get():
                if event.type == KEYDOWN:
                    if event.key == K_p:
                        running = False
                    elif event.key == K_p:
                        running = False

                elif event.type == QUIT:
                    self.game_over = True
                    running = False
                elif event.type == self.ADDCLOUD:
                    new_cloud = Cloud()
                    clouds_Added.add(new_cloud)

            clouds_Added.update()
            self.screen.fill(LIGHT_MODE)

            self.screen.blit(Text, Text_center)
            self.screen.blit(TextStart, TextStart_center)

            for entity in clouds_Added:
                self.screen.blit(entity.surf, entity.rect)

            pygame.display.flip()
            self.clock.tick(30)
        
if __name__ == "__main__":
    joc = Joc()

