# SD Studio - Android Application

SD Studio is an Android application built with Kotlin and Jetpack Compose, designed for AI-powered image generation. The app uses a modern Android development stack with Gradle build system and targets Android API 28+ devices.

Always reference these instructions first and fallback to search or bash commands only when you encounter unexpected information that does not match the info here.

## Critical Build Limitations

**NETWORK CONNECTIVITY RESTRICTIONS**: This development environment has severe network limitations:
- Google Maven repositories (dl.google.com, repo1.maven.org) are BLOCKED
- Standard Android Gradle Plugin downloads FAIL due to firewall restrictions
- Maven Central and other common Android development repositories are INACCESSIBLE
- GitHub.com connectivity is also restricted

**BUILD STATUS**: The project CANNOT build in this environment due to network restrictions blocking Android Gradle Plugin downloads. Do NOT attempt standard Gradle builds - they will fail after 1-5 minutes with network timeout errors.

## Working Effectively

### Environment Prerequisites
- Java 17 is available and correctly configured (`/usr/lib/jvm/temurin-17-jdk-amd64`)
- Android SDK is installed at `/usr/local/lib/android/sdk`
- Gradle 9.0.0 is available but plugins cannot be downloaded
- Available Android SDK components:
  - Build Tools: 34.0.0, 35.0.0, 35.0.1, 36.0.0
  - Platform Tools: adb, fastboot, etc.
  - Command Line Tools: lint, sdkmanager, avdmanager, etc.

### Repository Structure
```
.
├── .github/workflows/android.yml    # CI workflow (designed for environments with network access)
├── app/
│   ├── build.gradle.kts            # App-level build configuration
│   ├── proguard-rules.pro          # ProGuard rules (currently empty)
│   └── src/main/
│       ├── AndroidManifest.xml     # App manifest
│       ├── java/com/unstable/confusion/
│       │   └── MainActivity.kt     # Main activity with Compose UI
│       └── res/values/strings.xml  # String resources
├── build.gradle.kts                # Project-level build configuration  
├── settings.gradle.kts             # Gradle settings
└── .gitignore                      # Git ignore rules
```

### Build Commands (WILL FAIL in this environment)
**DO NOT RUN** these commands - they are documented for reference only:
- `gradle assembleDebug` -- FAILS after 1-5 minutes due to network restrictions. NEVER CANCEL.
- `gradle tasks` -- FAILS immediately due to missing Android Gradle Plugin
- `./gradlew build` -- gradlew wrapper is MISSING from repository

### Working Alternatives

#### Code Analysis and Linting
- Use standalone lint: `$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/lint .` 
  - Takes ~12 seconds to complete
  - Will show "LintError" about Gradle project, but provides some analysis
  - Available lint categories: Correctness, Security, Performance, Usability, Accessibility, etc.

#### Direct File Analysis
- All source code is in `app/src/main/java/com/unstable/confusion/MainActivity.kt`
- View and edit Kotlin source files directly
- Check Android manifest: `app/src/main/AndroidManifest.xml`
- Review build configuration: `app/build.gradle.kts`

#### Android Tools Available
- `$ANDROID_SDK_ROOT/platform-tools/adb` - Android Debug Bridge
- `$ANDROID_SDK_ROOT/build-tools/35.0.0/aapt2` - Android Asset Packaging Tool
- `$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/lint` - Lint analysis
- `$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager` - SDK management

### Validation Scenarios

**CRITICAL**: Due to build limitations, you CANNOT run the app in this environment. Validation must be done through:

1. **Code Review and Static Analysis**:
   - Review Kotlin code in `MainActivity.kt` for syntax and logic errors
   - Check Android manifest for configuration issues  
   - Verify build.gradle.kts dependencies and versions are reasonable
   - Run: `$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/lint .` (12 seconds)

2. **File Structure Validation**:
   - Ensure all required Android project files are present
   - Check resource files in `app/src/main/res/`
   - Verify package structure matches manifest declarations

3. **Configuration Verification**:
   - Target SDK: 35, Min SDK: 28 (supports Android 9+)
   - Namespace: `com.unstable.confusion`
   - App name: "SD Studio" (from strings.xml)
   - Uses Jetpack Compose with Material 3 theming

**IMPORTANT**: You cannot build, run, or test the application functionality in this environment. Focus on code quality, static analysis, and file structure validation only.

## Project Details

### Technology Stack
- **Language**: Kotlin 2.0.20
- **UI Framework**: Jetpack Compose with Material 3
- **Build System**: Gradle with Kotlin DSL
- **Target Platform**: Android 9+ (API 28-35)
- **Architecture**: Single Activity with Compose UI

### Key Dependencies
- Androidx Core KTX 1.13.1
- Activity Compose 1.9.2  
- Compose BOM 2024.09.01
- Material 3 Components
- Navigation Compose 2.8.1
- WorkManager 2.9.1
- Lifecycle Runtime KTX 2.8.6

### Development Notes
- Main UI shows placeholder content with "Generate (coming soon)" button
- TODO: Navigate to Generate screen (see MainActivity.kt:25)
- ProGuard rules file exists but is empty - will need rules for native/Genie libs
- No test files currently present in the project
- Missing Gradle wrapper files (gradlew, gradle/wrapper/)

## Common Tasks

### Making Code Changes
1. Edit Kotlin source in `app/src/main/java/com/unstable/confusion/MainActivity.kt`
2. Modify Android manifest if needed: `app/src/main/AndroidManifest.xml`
3. Update string resources: `app/src/main/res/values/strings.xml`
4. Run lint for basic validation: `$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/lint .`

### Adding Dependencies
1. Edit `app/build.gradle.kts` dependencies block
2. Note: Cannot test dependency resolution due to network restrictions
3. Verify versions are reasonable and compatible with existing stack

### Troubleshooting
- **"Plugin not found" errors**: Expected due to network restrictions - ignore
- **Network timeout errors**: Expected for any Gradle operations - do not retry
- **Missing gradlew**: Normal for this repository - use system gradle if needed
- **Build failures**: Expected in this environment - focus on static code analysis

### CI/CD Information
The `.github/workflows/android.yml` workflow is designed for environments with full network access:
- Uses GitHub Actions with Android SDK setup
- Downloads Gradle 8.7 and required plugins
- Builds debug APK and uploads as artifact
- **Will succeed in GitHub Actions but fails in this restricted environment**

## File Locations Reference

### Frequently accessed files:
```bash
# Main source code
cat app/src/main/java/com/unstable/confusion/MainActivity.kt

# Build configuration  
cat app/build.gradle.kts
cat build.gradle.kts

# Android configuration
cat app/src/main/AndroidManifest.xml

# Resources
cat app/src/main/res/values/strings.xml
```

### Project structure:
```bash
# View full structure
tree -a -I '.git|.gradle'

# List app source
ls -la app/src/main/java/com/unstable/confusion/

# Check resources
ls -la app/src/main/res/values/
```

Always focus on static code analysis and file-based validation since dynamic testing and building are not possible in this environment.