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
