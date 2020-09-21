**Steps to add CodePush:**

**#Create project code push for app**:

Step:

1. Sign up account in CodePush: https://appcenter.ms
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
      In a terminal window opened at the root of a React Native project, enter the following line to add Crash and Analytics services to your app: run script `npm install appcenter appcenter-analytics appcenter-crashes --save-exact`, later run script `npm pod` or `yarn pod`
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

5. Install libary `react-native-code-push`
  - Run script `npm install react-native-code-push` or `yarn add react-native-code-push`
  - Run script `npm pod` or `yarn pod`

6. Login account CodePush
  - Run script `appcenter login`

7. Only config app Android

Link: https://github.com/microsoft/react-native-code-push/blob/master/docs/setup-android.md

8. Only config app iOS

Link: https://github.com/microsoft/react-native-code-push/blob/master/docs/setup-ios.md

9. Wrap root component with CodePush

    class GorillaDesk extends Component {
      UNSAFE_componentWillMount() {
        // Orientation.unlockAllOrientations();
      }
      componentDidMount() {
        /** Update is downloaded silently, and applied on restart (recommended) */
        codePush.sync(
          {
            updateDialog: null,
            installMode: codePush.InstallMode.IMMEDIATE,
            deploymentKey: Platform.OS === 'ios' ? CODEPUSH_KEY_IOS : CODEPUSH_KEY_ANDROID
          },
        );
      }

      componentWillMount() {
        // Ensure that any codePush updates which are
        // synchronized in the background can't trigger
        // a restart while this component is mounted.
        codePush.disallowRestart();
      }

      componentWillUnmount() {
        // Reallow restarts, and optionally trigger
        // a restart if one was currently pending.
        codePush.allowRestart();
      }

      render() {

        return (
          <Provider store={store} >
            <App />
          </Provider>
        );
      }
    }

    const codePushOptions = { checkFrequency: codePush.CheckFrequency.MANUAL };

    export default codePush(codePushOptions)(GorillaDesk);


