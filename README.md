## Multi Module Application Structure

### Modules

#### :app
- Contains Application, MainActivity, SplashActivity, and SplashFragment.

#### :core
- Houses core base classes and providers such as OkHttpClient, preferences data store, base network classes, etc.

#### :uimodule
- Contains custom views, view extensions, and base view classes.

#### :common
- **:common:ui**: Holds strings shared across the application.
- **:common:data**: Empty, meant for data layer classes shared between multiple feature modules.
- **:common:domain**: Empty, meant for domain layer classes shared between multiple feature modules.

#### :matchmaker
- **:matchmaker:common**: Holds shared code of the matchmaker feature.
  - **:matchmaker:common:data**: Data layer classes shared between the matchmaker module.
  - **:matchmaker:common:domain**: Empty, for domain layer classes shared between matchmaker modules.
  - **:matchmaker:common:ui**: Empty, for presenter layer classes shared between matchmaker modules.
- **:matchmaker:feed**: Contains feed page codes.
  - **:matchmaker:feed:ui**: UI layer classes of the feed page.
  - **:matchmaker:feed:data**: Data layer classes of the feed page.
  - **:matchmaker:feed:domain**: Domain layer classes of the feed page.
  - **:matchmaker:feed:composeui**: UI layer classes of the feedCompose page.
- **:matchmaker:profile**: Holds profile page codes which show liked profiles.
  - **:matchmaker:profile:ui**: UI layer classes of the profile page.
  - **:matchmaker:profile:data**: Data layer classes of the profile page.
  - **:matchmaker:profile:domain**: Domain layer classes of the profile page.

### Test Coverage

- **ClientGeneratorTest**
- **RickMortyUseCaseTests**
- **RickMortyDaoTest**
- **RickyMortyApiHelperImplTest**
- **RickMortyLikesRepositoryImplTest**
- **DataStoreTest**
- **CircleImageViewTest**
- **StandardCustomToolbarTest**
- **CharacterPagingSourceTest**
- **MainActivityTest**
  

### Tech Stack

#### Core Technologies
- **Android Gradle Plugin (AGP)**: 8.3.1
- **Kotlin**: 1.9.22

#### Libraries
- **AndroidX Core KTX**: 1.12.0
- **JUnit**: 4.13.2
- **Espresso Core**: 3.5.1
- **AppCompat**: 1.6.1
- **Material Components**: 1.11.0
- **Navigation Component**: 2.7.7
- **Paging Library**: 3.2.1
- **Hilt**: 2.50
- **DataStore**: 1.0.0
- **OkHttp**: 4.12.0
- **Retrofit**: 2.9.0
- **Kotlin Symbol Processing (KSP)**: 1.9.22-1.0.16
- **Lottie**: 6.1.0
- **Glide**: 4.15.1
- **Room**: 2.6.1
- **Shimmer**: 0.5.0

#### UI/Compose
- **Lifecycle**: 2.7.0
- **Activity Compose**: 1.8.2
- **Hilt Compose Navigation**: 1.2.0
- **ConstraintLayout**: 2.1.4
- **ConstraintLayout Compose**: 1.0.1
- **Coil Compose**: 2.5.0
- **Accompanist**: 0.32.0

#### Testing
- **JUnit**
- **Espresso Core**
- **Mockito**: 2.19.0
- **Mockito Android**: 5.11.0
- **MockK**: 1.13.10
- **Fragment Testing**: 1.6.2

#### Miscellaneous
- **Compose Bom**: 2024.03.00

#### Plugins
- **Android Application**
- **JetBrains Kotlin Android**
- **Hilt**
- **Navigation Safe Args**
- **Kotlin Symbol Processing (KSP)**
- **Kotlin Parcelize**
