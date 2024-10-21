import flet as ft
import requests

def format_title(title):
    return title.lower().replace(" ", "-")

# busca a playlist fixa no backend
def get_playlist():
    response = requests.get("http://localhost:8080/api/music/playlist")
    if response.status_code == 200:
        return response.json().get("musics", [])
    else:
        return []

def get_lyrics(title):
    # realiza a requisição para o backend em Java
    response = requests.get(f'http://localhost:8080/api/music/{title}')
    
    # verifica se a resposta foi bem-sucedida
    if response.status_code == 200:
        music_data = response.json()
        if music_data.get("title") == "Música não encontrada":
            return "Música não encontrada."
        
        # caso a música tenha sido encontrada, retorne os detalhes
        return f"Título: {music_data['title']}\nArtista: {music_data['artist']}\nLetras: {music_data['notations'][0]['lyrics']}"
    else:
        return f"Erro ao buscar a música: {response.status_code}"


def main(page: ft.Page):
    page.title = "Music Lyrics Viewer"
    page.bgcolor = ft.colors.BLACK
    page.padding = 20


    # search/enter
    title_input = ft.Container(
        content=ft.TextField(label="Título da Música", width=400, border_radius=8, on_submit=lambda e: on_search(e)),
        bgcolor=ft.colors.GREY_900,
        padding=10,
        border_radius=8
    )

    # exibe a letra da música
    lyrics_output = ft.Container(
        content=ft.Text("", color=ft.colors.WHITE, text_align=ft.TextAlign.LEFT),
        width=400,
        height=400,
        bgcolor=ft.colors.GREY_900,
        padding=20,
        border_radius=8
    )


    # playlist
    music_list = ft.ListView(
        spacing=10,
        expand=True,
        padding=10
    )

    music_list_container = ft.Container(
        content=music_list,
        width=400,
        height=300,
        bgcolor=ft.colors.GREY_900,
        padding=10,
        border_radius=8
    )


    # função de busca
    def on_search(e):
        title = title_input.content.value
        lyrics_output.content.value = get_lyrics(title)
        page.update()


    # carregar e exibir a playlist
    def load_playlist():
        playlist = get_playlist()  # carrega a playlist fixa
        for music in playlist:
            # adiciona os títulos das músicas na lista
            music_item = ft.TextButton(
                music["title"],
                on_click=lambda e, title=music["title"]: load_lyrics(title)
            )
            music_list.controls.append(music_item)
        page.update()


    # busca a letra da música
    def load_lyrics(title):
        lyrics_output.content.value = get_lyrics( format_title(title) )  # Atualiza o campo com a letra da música
        page.update()


    # carregar a playlist ao iniciar a aplicação
    load_playlist()


    # layout da interface
    page.add(
        ft.Row(
            controls=[
                ft.Column(
                    controls=[
                        title_input,
                        music_list_container  # playlist
                    ],
                    width=400,
                    spacing=20
                ),
                # coluna onde a letra da música será exibida
                lyrics_output
            ],
            alignment=ft.MainAxisAlignment.START
        )
    )


ft.app(target=main)