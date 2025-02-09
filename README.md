# Example Project

This is a quick example project for a basic Java/Gradle setup.

It will print out and write to json the top 25 popular movies per genre according to [TMDB](https://www.themoviedb.org/).

## Setup

### Create a token
- Sign up for a TMDB account
- Get a token from Settings -> API

### Environment variables

```shell
export TMDB_TOKEN=<TOKEN HERE>
```

## Run

### Through gradle
```shell
./gradlew run
```

### Through jar
```shell
./gradlew build
java -jar build/libs/gradle-example-<VERSION>-all.jar
```


