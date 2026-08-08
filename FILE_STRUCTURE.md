# Jani Player – Fájlstruktúra

A projekt teljes fájlstruktúrája automatikusan generálva.

```
./build.gradle
./.github/workflows/decode-zip.yml
./.github/workflows/generate-file-structure.yml
./.github/workflows/decode-wrapper.yml
./.github/workflows/build-apk.yml
./.github/workflows/release.yml
./.github/workflows/decode-wrapper-fresh.yml
./.github/workflows/decode-gradlew.yml
./v1.0
./.git/objects/pack/pack-dc8ad3d775801d99fdf8975ef2d7d42f2218d12b.idx
./.git/objects/pack/pack-dc8ad3d775801d99fdf8975ef2d7d42f2218d12b.rev
./.git/objects/pack/pack-dc8ad3d775801d99fdf8975ef2d7d42f2218d12b.pack
./.git/hooks/sendemail-validate.sample
./.git/hooks/push-to-checkout.sample
./.git/hooks/post-update.sample
./.git/hooks/prepare-commit-msg.sample
./.git/hooks/pre-receive.sample
./.git/hooks/pre-push.sample
./.git/hooks/update.sample
./.git/hooks/pre-merge-commit.sample
./.git/hooks/pre-applypatch.sample
./.git/hooks/fsmonitor-watchman.sample
./.git/hooks/pre-commit.sample
./.git/hooks/commit-msg.sample
./.git/hooks/applypatch-msg.sample
./.git/hooks/pre-rebase.sample
./.git/index
./.git/HEAD
./.git/logs/HEAD
./.git/logs/refs/heads/main
./.git/logs/refs/remotes/origin/main
./.git/description
./.git/refs/heads/main
./.git/refs/remotes/origin/main
./.git/config
./.git/shallow
./.git/FETCH_HEAD
./.git/info/exclude
./.git/config.worktree
./FILE_STRUCTURE.md
./gradle-wrapper.properties
./JaniPlayerMinimal
./settings.gradle
./gradlew.bat
./app/build.gradle
./app/   src/     main/       java/         com/janiplayer/ui/video/VideoPlayerScreen.kt
./app/   src/     main/       java/         com/           janiplayer/filemanager/FileManagerNavigation.kt
./app/   src/     main/       java/         com/           janiplayer/filemanager/FileScanner.kt
./app/   src/     main/       java/         com/           janiplayer/filemanager/FileManagerViewModel.kt
./app/   src/     main/       java/         com/           janiplayer/filemanager/FileManagerScreen.kt
./app/   src/     main/       java/         com/           janiplayer/filemanager/FileItem.kt
./app/   src/     main/       java/         com/           janiplayer/             ui/               navigation/AppNavHost.kt
./app/   src/     main/       java/         com/           janiplayer/             ui/playlist/PlaylistScreen.kt
./app/   src/     main/       java/         com/           janiplayer/             ui/               settings/SettingsScreen.kt
./app/   src/     main/       java/         com/           janiplayer/             ui/               settings/AudioEffectsSettingsScreen.kt
./app/   src/     main/       java/         com/           janiplayer/viewmodel/video/VideoViewModel.kt
./app/   src/     main/       java/         com/           janiplayer/viewmodel/PlaylistViewModel.kt
./app/   src/     main/       java/         com/           janiplayer/MainActivity.kt
./app/proguard-rules.pro
./app/janiplayer.keystore
./app/src/main/proto/dsp_prefs.proto
./app/src/main/AndroidManifest.xml
./app/src/main/res/values/strings.xml
./app/src/main/res/values/ic_launcher_background.xml
./app/src/main/res/layout/activity_main.xml
./app/src/main/res/drawable/ic_launcher_foreground.png
./app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml
./app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml
./app/src/main/java/com/janiplayer/ui/settings/SettingsScreen.kt
./app/src/main/java/com/janiplayer/ui/settings/AudioEffectsSettingsScreen.kt
./app/src/main/java/com/janiplayer/ui/playlist/PlaylistScreen.kt
./app/src/main/java/com/janiplayer/ui/player/PlayerScreen.kt
./app/src/main/java/com/janiplayer/ navigation/AppNavHost.kt
./app/src/main/java/com/janiplayer/viewmodel/playlist/PlaylistViewModel.kt
./app/src/main/java/com/janiplayer/viewmodel/video/VideoViewModel.kt
./app/src/main/java/com/janiplayer/viewmodel/player/PlayerViewModelFactory.kt
./app/src/main/java/com/janiplayer/viewmodel/player/PlayerState.kt
./app/src/main/java/com/janiplayer/viewmodel/player/PlayerViewModel.kt
./app/src/main/java/com/janiplayer/audioeffects/AudioEffectsEngine.kt
./app/src/main/java/com/janiplayer/audioeffects/viewmodel/DspViewModel.kt
./app/src/main/java/com/janiplayer/audioeffects/viewmodel/DspViewModelFactory.kt
./app/src/main/java/com/janiplayer/audioeffects/data/DspConfig.kt
./app/src/main/java/com/janiplayer/audioeffects/data/DspRepository.kt
./com/janiplayer/ui/audio/AudioPlayerScreen.kt
./com/janiplayer/ui/settings/SettingsScreen.kt
./com/janiplayer/ui/util/Interactions.kt
./com/janiplayer/ui/animation/Animations.kt
./com/janiplayer/ui/video/VideoPlayerScreen.kt
./com/janiplayer/ui/theme/UiDefaults.kt
./com/janiplayer/ui/theme/Theme.kt
./com/janiplayer/ui/components/JaniListItem.kt
./com/janiplayer/video/ui/VideoOverlay.kt
./com/janiplayer/video/ui/VideoPlayerScreen.kt
./com/janiplayer/video/ui/PlayerLifecycleFix.kt
./com/janiplayer/video/ui/OptimizedPlayerView.kt
./com/janiplayer/video/ui/FullscreenController.kt
./com/janiplayer/video/player/OptimizedPlayer.kt
./com/janiplayer/video/player/VideoPlayerController.kt
./com/janiplayer/video/player/VideoSource.kt
./com/janiplayer/video/viewmodel/PositionState.kt
./com/janiplayer/navigation/AppNavGraph.kt
./com/janiplayer/data/playlist/PlaylistRepository.kt
./diag_modules.txt
./java/com/janiplayerminimal/MainActivity.kt
./buildlog.txt
./diag_gradle.txt
./local.properties
./viewmodel/PlaylistViewModel.kt
./README.md
./android home_/usr/lib/Ggggggg
./audioeffects/ui/AudioEffectsDspUI.kt
./audioeffects/     src/         main/             java/                 com/                     janiplayer/                         audioeffects/AudioEffectsDataStore.kt
./audioeffects/     src/         main/AndroidManifest.xml
./audioeffects/     src/main/java/com/janiplayer/audioeffects/AudioEffectsViewModel.kt
./audioeffects/     src/main/java/com/janiplayer/audioeffects/AnimatedAudioEffectsPanel.kt
./audioeffects/build.gradle.kts
./audioeffects/src/main/java/com/janiplayer/audioeffect/PresetSelector.kt
./audioeffects/src/main/java/com/janiplayer/audioeffects/EffectSlider.kt
./audioeffects/src/main/java/com/janiplayer/audioeffects/VideoPlayerScreen.kt
./audioeffects/src/main/java/com/janiplayer/audioeffects/AudioEffectsMainScreen.kt
./audioeffects/src/main/java/com/janiplayer/audioeffects/AnimatedAudioEffectsPanel.kt
./audioeffects/src/main/java/com/janiplayer/audioeffects/EqualizerBandSlider.kt
./audioeffects/viewmodel/DspViewModel.kt
./audioeffects/data/EqPreset.kt
./audioeffects/data/DspConfig.kt
./audioeffects/data/DspRepository.kt
./audioeffects/data/DspPreferencesSerializer.kt
./audioeffects/data/EqPresets.kt
./gradlew
```
