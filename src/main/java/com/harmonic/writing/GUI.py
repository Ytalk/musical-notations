import flet as ft
import requests

def get_lyrics(title):
    # Realizar a requisição para o backend em Java
    response = requests.get(f'http://localhost:8080/api/music/{title}')
    
    # Verificar se a resposta foi bem-sucedida
    if response.status_code == 200:
        music_data = response.json()
        if music_data.get("title") == "Música não encontrada":
            return "Música não encontrada."
        
        # Caso a música tenha sido encontrada, retorne os detalhes
        return f"Título: {music_data['title']}\nArtista: {music_data['artist']}\nLetras: {music_data['notations'][0]['lyrics']}"
    else:
        return f"Erro ao buscar a música: {response.status_code}"

# Função de GUI para exibir a letra
def main(page: ft.Page):
    page.title = "Music Lyrics Viewer"
    title_input = ft.TextField(label="Título da Música")
    lyrics_output = ft.Text()

    def on_search(e):
        title = title_input.value
        lyrics_output.value = get_lyrics(title)
        page.update()

    search_button = ft.ElevatedButton("Buscar Letra", on_click=on_search)
    
    page.add(title_input, search_button, lyrics_output)

# Executar a interface Flet
ft.app(target=main)