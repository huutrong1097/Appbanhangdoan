# Add CodePush: 

**Add SDK CodePush**:

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

**Deployment and release CodePush**:
**Note**:
Before you can begin releasing app updates, you must sign-in with your existing CodePush account or create a new App Center account. You can do this by running the following command once you've installed the CLI: `appcenter login`

**Deployment script structure**

    appcenter codepush release-react -a <ownerName>/<appName> -d <deploymentName> -t <targetBinaryVersion>
    [-t|--target-binary-version <targetBinaryVersion>]
    [-o|--output-dir]
    [-s|--sourcemap-output]
    [-c|--build-configuration-name <arg>]
    [--plist-file-prefix]
    [-p|--plist-file]
    [-g|--gradle-file]
    [-e|--entry-file]
    [--development]
    [-b|--bundle-name <bundleName>]
    [-r|--rollout <rolloutPercentage>]
    [--disable-duplicate-release-error]
    [-k|--private-key-path <privateKeyPath>]
    [-m|--mandatory]
    [-x|--disabled]
    [--description <description>]
    [-d|--deployment-name <deploymentName>]
    [-a|--app <ownerName>/<appName>]
    [--disable-telemetry]
    [-v|--version]

**Patch metadata update**

    appcenter codepush patch -a <ownerName>/<appName> <deploymentName> <existing-release-label>
    [-r|--rollout <rolloutPercentage>]
    [-d|--description <description>]
    [-t|--target-binary-version <targetBinaryVersion>]
    [-a|--app <ownerName>/<appName>]
    [--disable-telemetry]
    [-v|--version]

**Example**

  - Build Production iOS
  `appcenter codepush release-react -a nhan.phan-namlongsoft.net/Gorilla-Desk-iOS -d Production`
  - Build Staging iOS
  `appcenter codepush release-react -a nhan.phan-namlongsoft.net/Gorilla-Desk-iOS -d Staging`
  - Give production updates only to 20 percent of your users
  `appcenter codepush release-react -a nhan.phan-namlongsoft.net/Gorilla-Desk-iOS -d Production -r 20`
  - Provide a Staging build for Production
  `appcenter codepush promote -a <ownerName>/<appName> -s Staging -d Production`
  - Provide a Staging build for Production only 20 percent of your users
  `appcenter codepush promote -a <ownerName>/<appName> -s Staging -d Production -r 20`
  - The command update rollout the previous version. Then, after waiting for a reasonable amount of time to see if there are any crash reports or customer feedback, you can extend it to your entire audience by running
  `appcenter codepush patch -a <ownerName>/<appName> <deploymentName> -r 100`
  - The command gets a list of project name
  `appcenter apps list`
  - The command get the deploy key
  `appcenter codepush deployment list -a nhan.phan-namlongsoft.net/Gorilla-Desk-Android -k`
  - The command gets a list of deployment status
  `appcenter codepush deployment list -a <ownerName>/<appName>`
  - See release history
  `appcenter codepush deployment history -a <ownerName>/<appName> <deploymentName>`
  - Clearing Release History
  `appcenter codepush deployment clear -a <ownerName>/<appName> <deploymentName>`

