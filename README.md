# stream-rest-parent

This is a example project for use **Streaming Response** with **Spring Boot**.

## Running the application

For running the application, use the following command:

```bash
gradlew bootRun
```

## Benchmarks

## Prerequisites

* [Apache Bench](http://httpd.apache.org/docs/2.2/programs/ab.html)

Install Apache Bench on Mac OS X using [Homebrew](http://brew.sh/):

```bash
brew install apache-bench
```

### Running the benchmarks

The benchmarks are located in the `benchmarks` module. To run them, use the following command:

```bash
ab -n 2 -c 2 http://localhost:3001/reader
```
