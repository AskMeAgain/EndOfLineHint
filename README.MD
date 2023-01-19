# Intellij Plugin Template

This is a complete template to push your Plugins much faster to the store.

Everything is already setup and an explanation for all needed manual steps is on the bottom.

## Setup

### Download template

In Github just click the green "Use this template" button

### Develop your plugin

Develop the plugin.

PS: the docu is pretty shitty and google is not your
friend here if you want to do anything more complicated. 
You have to ask in the Intellij Forum quite some times, but you 
will always get an answer.

### Create a manual release of the plugin

1. go to the following page (create an account if you havent)

       https://plugins.jetbrains.com/author/me

2. click on your profile
3. Upload plugin
4. fill out everything (use unstable channel for the manual release)

### Initialize secrets

1. execute the scripts to create all needed files. Keep the password in mind!

       ./create-secrets.sh

2. go to your intellij author page and create a new publish token

       https://plugins.jetbrains.com/author/me

3. click on your profile
4. my profile
5. my tokens
6. Generate new Token

### Setup GitHub CI variables

1. Go to "Settings" on your new GitHub repository
2. Secrets
3. Actions
4. New Repository Secret
5. Create the following 4 secrets
    * | Name                 | Content                                                     |
      |----------------------|--------------------------------------------------------------|
      | CERTIFICATE_CHAIN    | The file is called CERTIFICATE_CHAIN.crt in the secrets folder |
      | PRIVATE_KEY          | The file is called PRIVATE_KEY.pem inside the secrets folder |
      | PRIVATE_KEY_PASSWORD | the password you entered while executing create-secrets.sh  |
      | PUBLISH_TOKEN        | The token from the intellij website                         |
    * For the files, just copy the content into the window text field without any formatting

### Create a store page

Fill our `marketplace-description.html`. This will be shown in the Intellij Marketplace store page

For updates, change `marketplace-changenotes.html`.

### How to make a release

If you have created your first manual release, you can for now create all released by hand

0. Dont forget to fill out `marketplace-changenotes.html`
1. Just create a new GitHub release
2. The git tag will be your version. 
3. Name it "1.0.0" or "0.1.2" or "10.0.123"
4. It will always be pushed to Stable (the marketplace)
5. If the upload worked you will receive an email
6. You will receive another email if the manual approval went through.
7. Profit!



