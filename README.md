# testproject-wait-for-spinner

Waits patiently for a spinner to disappear from the UI.

It has these defaults:

- xpath of element to find: `//div[contains(@class,"spinner") and not(contains(@style,"none"))]`
- time between each check: `1 second`
- maximum number of checks: `120`
- number of sequential checks to make to declare the spinner gone: `2`

This means it looks for a `<div class="spinner ...">` in the DOM that doesn't have a style="display: none" in it.

![Screenshot of the plugin](docs/screenshot.png)

## Build

You need to first download the TestProject SDK, which is available from their website.

Download it and place it into: `/sdk/io.testproject.sdk.java.jar`.  I haven't included it here as it's not mine to license.

When building, you need to manually do these things:

1. Update the version in `manifest.json`.
2. Run the gradle build process (I use IntelliJ IDEA)
3. Upload `build/lib/WaitForSpinner.jar` into TestProject's addon's section 

This has been built against version 0.65.0 of the TestProject SDK using Gradle 7 from within IntelliJ IDEA.
If you update the SDK, you must manually update `src/main/testproject-sdk.properties`

## Developing

Some references to get started:

- https://docs.testproject.io/testproject-addons/develop-an-addon
- https://github.com/testproject-io/addons/
- https://intercom.help/testprojectio/en/articles/4570786-how-to-create-jar-file-from-java-project-with-testproject-sdk-and-upload-it-as-a-coded-test

The code is `WaitForSpinner.java`. 

It's pretty self-explanatory — here's some pseudocode.

```
do {
   check for spinner
   wait
} while (spinner is in the DOM && not timed out)
```

## Tests

Sorry, none. I'm not a Java developer so this is the best you're gonna get.

## License, warranty & Support

Licensed under MIT. No warranty or support. Good luck!