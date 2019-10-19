# GBLib
General robot code, usable in essentially every season

## Usage
This project relies upon [jitpack](https://jitpack.io/) in order to implement itself inside other projects.

In order to add this project you will need to use [gradle](https://gradle.org/). In your `build.gradle` file you should add the section:

    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
        mavenCentral()
    }

And alongside that, in your `dependencies` section add the line:

    compile 'com.github.GreenBlitz:GBLib:master-SNAPSHOT'

This should add the most recent release of the software into your project. In case this results
in issues, try to replace the `master-SNAPSHOT` part with the hash the the most recent commit (If you
do it that way, you will need to update the hash manually).

## Contributing

### If you are currently a member of GreenBlitz
This project follows [gitflow](https://danielkummer.github.io/git-flow-cheatsheet/) to manage
it's branches. Therefore, if you are interested in fixing a bug, checkout a new bugfix branch from `dev`.
If you are interested in adding a feature, checkout a new feature branch from `dev`.

After you finish your changes create a pull request into `dev`. When time comes, the current supporters
of this project will merge `dev` into `master` and your change will take effect.

### If you are not a member of GreenBlitz
Create a fork of this project, in this fork you are welcome to edit the project as you wish. When you are
finished create a pull request into `dev`. Please create a new pull request for each individual feature or bugfix.

When time comes, the current supporters
of this project will merge `dev` into `master` and your change will take effect.
