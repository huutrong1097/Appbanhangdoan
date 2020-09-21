9. Wrap root component with CodePush

    `class GorillaDesk extends Component {
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
    export default codePush(codePushOptions)(GorillaDesk);`


