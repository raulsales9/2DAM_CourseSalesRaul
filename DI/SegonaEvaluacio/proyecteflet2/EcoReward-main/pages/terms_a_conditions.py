import flet as ft
from flet import *

class TermsAndConditions(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        self.page.title = "Terms & conditions"
        self.page.theme_mode = ThemeMode.LIGHT
        self.page.padding = 20
        self.page.window_width = 430
        self.page.window_height = 932
        self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        self.page.vertical_alignment = ft.MainAxisAlignment.CENTER
        self.page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
        self.page.update()

    def build(self):

        terms_and_conditions_text = """TERMS AND CONDITIONS
        These Terms and Conditions set out the terms under which we use and protect the information provided by our users when they use our website. This company is committed to ensuring your privacy is protected.
        Information We Collect:
        - Name: We collect your name to personalize the user experience and communicate effectively.
        - Contact Information: This includes your email address to send you updates, special offers, and other relevant information.
        - Demographic Information: We may collect demographic data to better understand our audience and improve our products and services.
        Use of Collected Information:
        - Provide the best possible service.
        - Maintain a record of users and orders.
        - Periodically send emails with special offers and updates.
        Data Security: We are committed to keeping your information secure. We use advanced systems and regularly update them to prevent unauthorized access.
        Cookies: Cookies help us provide personalized service and enhance your experience on our website.
        Please note that these Terms and Conditions may change over time, so we recommend reviewing them periodically to ensure you agree with any updates.
        """

       
        return ft.ResponsiveRow([
                ft.Container(
                    ft.TextField(
                        value=terms_and_conditions_text,
                        multiline=True,
                        text_align=ft.TextAlign.JUSTIFY,
                        read_only=True,
                        text_size=13,
                        border=ft.InputBorder.NONE,
                    ),
                    width=430,
                    height=700,
                ),
            ])

if __name__ == '__main__':
    def main(page: Page):
        terms_and_conditions = TermsAndConditions(page)
        page.add(terms_and_conditions)
    ft.app(target=main, assets_dir="../assets/")
