# PNG details app

PNG details app (Name subject to change) is an app inspired by boredom.

The app reads contents of a PNG image according to [RFC 2083](https://datatracker.ietf.org/doc/html/rfc2083). The app also prints out the amount of chunks it found and their names.

## Requirements

* Java17
* Gradle for compiling the program
* A PNG image ;)

## Running the app

If you cloned the repository, you should be able to compile it inside the folder with gradle by using command 

`gradle jar`

Then, the application can simply be ran with 

`java -jar app/build/libs/app.jar FILEPATH_TO_YOUR_IMAGE`

### Example output as of 23.3.2022:

```
Found 7 chunks of data in the image:
IHDR
tEXt
tIME
pHYs
gAMA
IDAT
IEND
```