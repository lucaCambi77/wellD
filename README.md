# WellD code challenge

## Requirements : 

* Java11
* Gradle

## Getting Started

* To build the project 
```bash
./gradlew build
```

## Run 

* To run the application 

```bash
./gradlew bootRun
```

##Endpoints : 

* Add a point to the space 

``POST - http://localhost:8083/wellD/point``

* Get all points in the space

`GET - http://localhost:8083/wellD/space`

* Get all line segments passing through at least n points

`GET - http://localhost:8083/wellD/point/{n}`

* Remove all points from the space

`DELETE - http://localhost:8083/wellD/space`
