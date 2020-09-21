# Appbanhangdoan
![](https://drive.google.com/file/d/1moLDeCQB-mIYqlOkeE6G3V4JBarD3FOl/view?usp=sharing)

**Steps to add CodePush:**
**#install lib**:

1. npm install --save react-native-code-push
2. yarn pod or npm pod

**#Create project code push for app**:
Step:

1. Access link: https://appcenter.ms/
2. Create project for Android/iOS
3. Config Android:
  - Add the SDK to the project:
      In a terminal window opened at the root of a React Native project, enter the following line to add Crash and Analytics services to your app: run script `npm install appcenter appcenter-analytics appcenter-crashes --save-exact`
  - Integrate the SDK:
      Create a new file with the filename `appcenter-config.json` in `android/app/src/main/assets/` with the following content:

          {
          "app_secret": "5b45ff46-8a54-4758-b5b9-dbba4e7d1c43"
          }

      Modify the app's res/values/strings.xml to include the following lines:

          <string name="appCenterCrashes_whenToSendCrashes" moduleConfig="true" translatable="false">DO_NOT_ASK_JAVASCRIPT<string>
          <string name="appCenterAnalytics_whenToEnableAnalytics" moduleConfig="true" translatable="false">ALWAYS_SEND</string>

4. Config iOS:
  - Add the SDK to the project:
      In a terminal window opened at the root of a React Native project, enter the following line to add Crash and Analytics services to your app: run script `npm install appcenter appcenter-analytics appcenter-crashes --save-exact`
  - Integrate the SDK:
      Run pod install from iOS directory to install CocoaPods dependencies.
      Note: Integrating the iOS SDK requires CocoaPods. If you want to integrate manually, follow these steps.
      Create a new file with the name `AppCenter-Config.plist` with the following content. Don't forget to add this file to the Xcode project (right-click the app in Xcode and click Add files to <App Name>...).

          <?xml version="1.0" encoding="UTF-8"?>
          <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "https://www.apple.com/DTDs/PropertyList-1.0.dtd">
          <plist version="1.0">
          <dict>
          <key>AppSecret</key>
          <string>977e8fba-8601-4277-a2fe-661c0771a085</string>
          </dict>
          </plist>

      Modify the app's AppDelegate.m file to include code for starting SDK:
      Add these lines to import section

          #import <AppCenterReactNative.h>
          #import <AppCenterReactNativeAnalytics.h>
          #import <AppCenterReactNativeCrashes.h>

      Add these lines to the didFinishLaunchingWithOptions method

          [AppCenterReactNative register];
          [AppCenterReactNativeAnalytics registerWithInitiallyEnabled:true];
          [AppCenterReactNativeCrashes registerWithAutomaticProcessing];
