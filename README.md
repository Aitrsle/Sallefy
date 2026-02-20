# 🎧 Lasallefy --- Reproductor basado en sintetizador

En este proyecto vamos a ampliar el SoundSynth para convertirlo en un **reproductor de "canciones"** con:

- Listas de reproducción (playlists)
- Biblioteca de canciones
- Reproducción usando el `SoundSynth`

---

# 1. Contexto general

El sistema será un mini reproductor musical basado en vuestro
sintetizador.

No usa MP3, sino una secuencia de **notas/frecuencias** generadas con
`SoundSynth`.

Características clave:

- Algunas canciones tienen notas → **son reproducibles**
- Otras solo tienen metadatos → **solo se listan**
- Las playlists contienen **referencias (ids)** a canciones, no copias

El usuario podrá:

- Gestionar canciones
- Gestionar playlists
- Reproducir canciones y playlists

> ⚠️ Toda la información se mantendrá en memoria durante la ejecución
> del programa.

---

# 2. Modelo de dominio mínimo

## 2.1 Notas (`Note`)

La nota puede ser un sonido o un silencio.  Debe contener la información necesaria para poder ser reproducida por el SoundSynth:

- frecuencia o nota (en herzios)
- tiempo (en milisegundos)
- timbre (qué tipo de onda utiliza: triangulo, seno, diente de sable... )

Investiga y crea los atributos que necesites.

## 2.1 Canciones (`Song`)

Cada canción incluye:

- `id`
- `title`
- `artist`
- `durationSeconds`
- `mood` (enum) → `HAPPY`, `SAD`, `RELAX`, `ENERGETIC`, etc.
- `style` (String o enum)
- `playable` (boolean o derivado)
- Datos de reproducción opcionales:
  - Lista de `(note, durationMs)`
  - O lista de `(frequency, durationMs)`

### Requisitos importantes

Debe existir la posibilidad de que **una canción no tenga notas**, solo
metadatos.

En los listados debe aparecer si es:

- `[PLAYABLE]`
- `[NOT PLAYABLE]`

---

## 2.2 Playlists (`Playlist`)

Cada playlist contiene:

- `id`
- `name`
- `description`
- `mood`
- Lista de **ids de canciones**

Debe calcular dinámicamente:

- Duración total
- Número de canciones reproducibles / no reproducibles

---

# 3. Funcionalidades mínimas

## 3.1 Menú principal

    ■■■ Lasallefy ■■■

    1. Gestionar canciones
    2. Gestionar playlists
    3. Reproducir
    Q. Salir

---

## 3.2 Gestión de canciones

### ✔ Listar canciones

Mostrar:

- id, título, artista, duración, mood, style
- Estado `[PLAYABLE]` o `[NOT PLAYABLE]`

### ✔ Añadir canción

Solicitar:

- Título
- Artista
- Duración
- Mood
- Estilo
- ¿Es reproducible?

Si es reproducible:

- introducir estructura simple de notas.

### ✔ Eliminar canción

(Solo si no está en playlists, o mostrando aviso)

---

## 3.3 Gestión de playlists

### ✔ Crear playlist

Solicita:

- Nombre
- Descripción
- Mood

### ✔ Añadir canción a playlist

- Elegir playlist
- Elegir canción

### ✔ Eliminar canción de playlist

### ✔ Listar playlists

Mostrar:

- Nombre
- Número de canciones
- Duración total
- Reproducibles / no reproducibles

### ✔ Eliminar playlist

---

# 4. Reproducción con SoundSynth

## 4.1 Reproducir canción

- Se listan solo las reproducibles
- El usuario elige una
- Se recorre la secuencia de notas/frecuencias
- Por cada nota se invoca:

```
    makeSound(frequency, durationMs)
```

# 5. Arquitectura y diseño (POO)

Se espera una arquitectura **por capas**

## 5.1 Capas mínimas

### Model

- Song
- Sound
- Note
- Silence
- Playlist
- enums

### Controller

Coordina el menú y la lógica de aplicación.

### View

Debe ser una intefaz. El controller debería estar acoplado a una interfaz.

Interacción por consola.

> ❌ No se permite acceso directo desde la vista al modelo.
> ❌ No se permite lógica de negocio en `Main`.

---

## 5.2 Requisitos de POO

El proyecto debe demostrar:

- Encapsulamiento
- Herencia
  - `abstract class SoundSynth`
  - Subclases: `SoundSynthSinus`, `SoundSynthSquare`, etc.
- (Opcional) interfaz `Playable`
- Uso correcto de colecciones (`ArrayList`, `HashMap`)

---

# 6. Robustez y manejo de errores

La aplicación debe:

- Validar entradas del usuario
- Evitar ids inexistentes
- Impedir reproducir canciones no PLAYABLE


- Evitar `NullPointerException`
- No imprimir stacktraces en la vista

---

# 7. Entrega

Incluye:

1.  Código completo organizado por paquetes
2.  Datos iniciales cargados en el sistema (mínimo 3-5 canciones)
3.  Diagrama UML de clases
4.  README explicativo
