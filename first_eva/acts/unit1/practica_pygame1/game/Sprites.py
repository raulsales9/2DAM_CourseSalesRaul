import pygame

# Esta función se encargará de actualizar y dibujar todos los sprites
def update_and_draw_sprites(screen, all_sprites, player, enemies):
    screen.fill((0, 0, 0))  # Limpia la pantalla con un fondo negro

    for entity in all_sprites:
        screen.blit(entity.surf, entity.rect)

    # Verifica la colisión entre el jugador y los enemigos
    if pygame.sprite.spritecollide(player, enemies, False):
        player.kill()
        pygame.quit()  # Cierra Pygame

    pygame.display.flip()  # Actualiza la pantalla

# Agrega otras funciones o lógica relacionada con los sprites si es necesario
