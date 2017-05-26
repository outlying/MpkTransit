# Google Transit data generator for MPK Kraków

The goal of the project is to convert MPK Kraków public transport timetables to GTFS format supported by Google Transit.

Google Transit data is available on Google Maps so that you can search for public transit connections directly on Google Maps.

## Run

To see how this projects is working just execute

    ./gradlew run
    
After few seconds code will be compiled and executed, results will be stored in `{project.root}/creator/build/transit` in GTFS format.

It's possible to define custom output directory by adding extra argument like this

    ./gradlew run -Pargs={targetDir}
    
If directory don't exist it will be created, exiting output files will be replaced by new ones.

Additional system configuration may be required.

For Windows, use `gradlew.bat`

## Data formats

More about currently used data formats and sources

### MPK

SQLite database downloaded from MPK website is used as data feed. The database is not documented so the meaning of the data must be inferred.

### GTFS (Google Transit)

Google Transit is using GTFS to gather information about public transportation timetables.

Detailed information about GTFS:
* [Google Transit, GTFS Static Overview](https://developers.google.com/transit/gtfs/)
* [Wikipedia (en)](https://en.wikipedia.org/wiki/General_Transit_Feed_Specification)

#### GTFS Realtime

At the moment, this project does not generate GTFS Realtime compliant data.