# Performance_Automation based on Gatling 

- This is a sample performance automation project for social media app.
- It is written by Scala and execute with Maven Gatling.

**_This is currently just starting... to develop..._**

## Directory Structure

```bash
.
├── pom.xml                   # maven pom file
├── src
│   └── test
│       ├── resources         # resource files like csv
│       └── scala
│           ├── commons
│           ├── headers
│           ├── main          # gatling simulation directory 
│           ├── pages
│           ├── apis
│           ├── scenarios
│           └── variables
└── target
    ├── gatling               # gatling report here
    └── site
        └── scaladocs
```

## Usage:

To test it out, simply execute the following command:
```bash
mvn gatling:test -Dgatling.simulationClass=main.SimulationTps -DScenarioName=all
mvn gatling:test -Dgatling.simulationClass=main.SimulationUser -DScenarioName=scn1,scn2 -DProxy

```

### scaladoc
```bash
mvn scala:doc # generated to target/site/scaladocs/index.html
```
