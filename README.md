# [Hotel Management System](./docs/task.pdf)

## Tech Stack

### Frontend
- React
- React Router
- Chakra UI

### Backend
- Spring Boot
- Spring Web extension

### Dev Dependencies
- Spring Boot DevTools (enables automatic server restart when a change in files is detected)

## Workflow

Everything below can be used from a bare command line or an IDE of your choice. In Intellij IDEA,
you can create a shortcut to any of these commands and make them run in integrated terminals by
creating configurations.

### Developing

To start working on the app, you have to start front- and backend parts separately. To start the
Java Servlet, run 
```shell
./gradlew bootRun -Dspring.devtools.restart.enabled=true
```

This will start an application instance on localhost:8080 by default.

The React app resides in the `frontend` directory, so to run a start script, you have to be in that
directory (keep that in mind when creating run configurations for your IDE, as well). To run it, you
have to install a node package manager called `yarn`. If you already have `npm`, run

```shell
npm i -g yarn@latest
```

Once installed, 

```shell
yarn start
```

will start the app for you. **Please, do not mix different packagers - use yarn only!**

The React app will start on localhost:3000 and it is configured to pass all unknown requests
(the requests which are not defined by it and will result in 404) to localhost:8080. Thanks to this,
we can `fetch()` any API endpoint of the Spring app from React during development!

### Building

You likely won't need to build the project while working on it - this is for deployment purpose only.
The project is configured to build as a WAR (Web Application Resource). To build a WAR file, which
is used for application deployment, run

```shell
./gradlew build
```

In the `build.gradle` file in the root of this repository, I have added a few commands to build the frontend
app using `react-scripts` before the servlets get built. This allows us to build the whole project using
single command.

The resulting .war file will be located in `build/libs/hotel-{version}-SNAPSHOT.war`. Running it is as simple
as:

```shell
java -jar hotel.war
```

## Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

