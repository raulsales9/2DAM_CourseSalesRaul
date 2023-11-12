import flet as ft

COLOR_BACKGROUND = ft.colors.WHITE

class Timeline:
    def __init__(self):
        self.timeline_data = ["Timeline 1", "Timeline 2", "Timeline 3"]

    def get_timeline(self):
        timeline_containers = []

        for timeline in self.timeline_data:
            timeline_container = ft.Container(
                ft.Text(timeline),
                bgcolor=COLOR_BACKGROUND,
                padding=16,
                margin={"bottom": 16},
                border_radius=8,  
            )
            timeline_containers.append(timeline_container)

        timeline_container = ft.Container(
            *timeline_containers,
            bgcolor=COLOR_BACKGROUND,  # Puedes cambiar esto al color que prefieras
            col={"xs": 12, "sm": 6, "md": 4, "lg": 3, "xl": 2},
        )

        return timeline_container
