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

# Função que busca a letra da música no backend
def get_lyrics(title):
    # Realiza a requisição para o backend em Java
    response = requests.get(f'http://localhost:8080/api/music/{title}')
    
    # Verifica se a resposta foi bem-sucedida
    if response.status_code == 200:
        music_data = response.json()
        if music_data.get("title") == "Música não encontrada":
            return {"title": "Música não encontrada.", "artist": "", "lyrics": ""}
        
        # Retorna o título, artista e letras separadamente
        return {
            "title": music_data["title"],
            "artist": music_data["artist"],
            "lyrics": music_data['notations'][0]['lyrics']
        }
    else:
        return {"title": f"Erro {response.status_code}", "artist": "", "lyrics": ""}


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


    # Exibe o título, artista e letra da música com scroll
    lyrics_output = ft.Column(
        controls=[],  # Inicialmente vazio, será preenchido com título, artista e letra
        scroll="auto"
    )

    lyrics_output_container = ft.Container(
        content=lyrics_output,
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
        load_lyrics(title)


    # carregar e exibir a playlist
    def load_playlist():
        playlist = get_playlist()  # carrega a playlist fixa
        for music in playlist:
            # adiciona os títulos das músicas na lista
            music_item = ft.TextButton(
                music["title"],
                on_click=lambda e, title=music["title"]: load_lyrics(title),
            )
            music_list.controls.append(music_item)
        page.update()


    # busca a letra da música
    def load_lyrics(title):
        music_data = get_lyrics(format_title(title))  # Busca as letras
        lyrics_output.controls.clear()  # Limpa o conteúdo anterior

        # Adiciona o título com fonte maior
        lyrics_output.controls.append(
            ft.Text(music_data["title"], size=24, weight="bold", color=ft.colors.WHITE)
        )
        
        # Adiciona o artista com fonte um pouco menor
        lyrics_output.controls.append(
            ft.Text(music_data["artist"], size=20, italic=True, color=ft.colors.WHITE)
        )

        # Adiciona as letras no tamanho padrão
        for line in music_data["lyrics"].split("\n"):
            lyrics_output.controls.append(
                ft.Text(line, size=16, color=ft.colors.WHITE)
            )

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
                lyrics_output_container
            ],
            alignment=ft.MainAxisAlignment.START
        )
    )


ft.app(target=main)