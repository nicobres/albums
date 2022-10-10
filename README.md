# Application Android d'une liste d'album

Application Android affichant une liste d'album à partir d'un fichier JSON reçu via un web-service. Persitance de cette liste d'album en local pour un fonctionnement offline.

## Architecture
Choix de l'architecture Model/View/ViewModel (MVVM) pour séparer le code de l'affichage des données (**View**), les données (**Model**) et la logique (**ViewModel**)
Cette séparation des responsabilités suit le principe Single Responsability Principle **SOLID** qui dit que chaque classe ne doit avoir qu'une seul raison de changée.
Ainsi :
- **View** : La View est un élément visuel, une page par exemple, qui définit la mise-en-page et l'apparence, mais également l'interaction utilisateur. La View définit le comportement visuel et réagit aux changements d'états du ViewModel.
- **ViewModel** : Le ViewModel encapsule la  _logique de présentation_  mais pas son affichage. Il expose des propriétés et des commandes que la View va observée
- **Model** : Le Model est en charge des données (persistance, appel réseaux ...)

## Librairies utilisées

- **Koin** : Librairie légère d'injection de dépendance. Simple d'usage avec courbe d'apprentissage modéré
- **Room** : Librairie de persistance de donnée (recommandée par Google)
- **Retrofit** : Librairie qui permet de simplifié les appels aux API Rest
- **Coroutines** : Librairie permettant de faire de l'asynchrone (recommandé par Google)
- **Glide** : Librairie d'affichage d'image à partir de son URL
- **Mockk** : Librairie Kotlin pour mocker des objets

## Tests unitaires
J'ai fais des tests unitaires pour tester le **Model**. Cela permet de s'assurer de son bon fonctionnement actuelle et future. Ainsi cela pourra lever une alerte après un éventuel changement qui casserait un ancien fonctionnement
Pour cela j'ai utilisé la librairie **Mockk** pour le mockage et **JUnit** pour les asserts
Le coverage du dossier _data_ est de **72%** (lines)