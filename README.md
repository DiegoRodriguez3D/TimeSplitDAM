# TimeSplitDAM
Proyecto de fin de ciclo Desarrollo de Aplicaciones Multiplataforma por Diego Rodriguez Barcala

Esta aplicación se ha desarrollado utilizando las siguientes tecnologías:
  - Java y XML (codificación y diseño de interfaz)
  - SQLite (Base de datos local)

Se ha utilizado el patrón MVC para estructurar el proyecto.

La aplicación permite al usuario crear y reproducir un temporizador de intervalos introduciendo los parámetros necesarios desde la pantalla "Quick Start". 
En caso de que el usuario se registre e inicie sesión, se permitirá la creación de perfiles para almacenar los temporizadores en la base de datos local. El usuario podrá iniciar los temporizadores pulsando en el respectivo perfil, sin necesidad de introducir nuevamente los parámetros.

Un temporizador consta de 3 tiempos:
  - Tiempo de preparación: Se reproduce al iniciar por primera vez la reproducción del temporizador.
  - Tiempo de trabajo: Se reproduce a continuación del tiempo de preparación. Tiempo en el cual el usuario desarrolla la actividad deseada.
  - Tiempo de descanso: Se reproduce a continuación del tiempo de trabajo. Tiempo en el cual el usuario realiza una pausa en la actividad.
  
Cada vez que se completa el ciclo "tiempo de trabajo - tiempo de descanso", se pasa a la siguiente ronda y se vuelve a iniciar el ciclo.
El temporizador de intervalos finaliza al llegar a la última ronda o cuando el usuario finalice la reproducción.


##GALERIA
![image](https://user-images.githubusercontent.com/69866476/233952044-0e957f04-9a66-45f2-955d-2b8160af8b34.png)
![image](https://user-images.githubusercontent.com/69866476/233952209-14d9d66c-e475-469d-9d68-981e91f37114.png)
![image](https://user-images.githubusercontent.com/69866476/233952287-b0d8d564-3d53-4feb-ab65-cd43b058d0e0.png)

