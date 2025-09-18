# SD Studio - Android Application

SD Studio is an Android Kotlin application built with Jetpack Compose that appears to be designed as a Stable Diffusion image generation interface. Currently in early development (v0.1.0) with placeholder UI.

**ALWAYS** reference these instructions first and fallback to search or bash commands only when you encounter unexpected information that does not match the info here.

## Working Effectively

### Prerequisites and Environment Setup
- Install JDK 17 or higher: `sudo apt update && sudo apt install openjdk-17-jdk`
- Verify Java version: `java --version` (should show version 17+)
- Android SDK must be available at `$ANDROID_SDK_ROOT` with:
  - Platform API 35 (`platforms/android-35/`)
  - Build Tools 35.0.0 (`build-tools/35.0.0/`)
  - Platform Tools (`platform-tools/`)
- Gradle 9.0.0+ is required

### Build Commands and Timing
**CRITICAL BUILD LIMITATION**: **ALL GRADLE COMMANDS FAIL** due to network restrictions preventing download of Android Gradle Plugin (com.android.application:8.5.2). Even basic gradle commands fail with:
```
Plugin [id: 'com.android.application', version: '8.5.2', apply: false] was not found
```

**All Gradle Commands (ALL FAIL)**:
- `gradle assembleDebug` -- **FAILS due to network restrictions** - Build would take ~2-3 minutes if plugin dependencies were available. NEVER CANCEL. Set timeout to 10+ minutes.
- `gradle assembleRelease` -- **FAILS for same reason** - Release build with ProGuard minification enabled  
- `gradle clean` -- **FAILS for same reason** - Cannot clean build artifacts
- `gradle tasks` -- **FAILS for same reason** - Cannot list gradle tasks
- `gradle projects` -- **FAILS for same reason** - Cannot show project structure

**No Gradle Wrapper**: Project does not include gradlew/gradlew.bat wrapper scripts.

### Development Workflow (Severely Limited by Build Issues)
Due to network restrictions preventing gradle plugin downloads:
- **You CANNOT run ANY gradle commands** (all fail during plugin resolution)
- **You CANNOT build or run the application**
- **You CANNOT run unit tests** (none exist in current codebase)
- **You CANNOT clean build artifacts**
- **You CAN** make code changes to Kotlin files
- **You CAN** modify build configurations and dependencies (but cannot test them)
- **You CAN** analyze code structure and dependencies using file system commands

### Manual Validation Requirements
**Current State**: Due to build failures, you cannot perform full end-to-end validation. When build issues are resolved:
- Build the debug APK: `gradle assembleDebug`
- Verify APK creation: `ls -la app/build/outputs/apk/debug/app-debug.apk`
- **Manual Testing Scenario**: Install APK on device/emulator and verify the main screen shows:
  - "SD Studio — v0.1.0" title
  - "Runtime: Genie/QNN (to be wired)" status
  - "Models: Not imported yet" status  
  - "Generate (coming soon)" button (non-functional placeholder)

## Project Structure and Key Files

### Repository Root
```
.
├── .github/
│   └── workflows/android.yml    # CI/CD pipeline (also fails due to same network issue)
├── .gitignore                   # Standard Android gitignore
├── app/                         # Main Android application module
├── build.gradle.kts            # Root project build script
└── settings.gradle.kts         # Gradle settings
```

### Application Module (`app/`)
```
app/
├── build.gradle.kts            # App module build configuration
├── proguard-rules.pro          # ProGuard rules for release builds
└── src/main/
    ├── AndroidManifest.xml     # App manifest (minimal configuration)
    ├── java/com/unstable/confusion/
    │   └── MainActivity.kt     # Single activity with Compose UI
    └── res/values/
        └── strings.xml         # App name: "SD Studio"
```

### Key Dependencies (from app/build.gradle.kts)
- **Target SDK**: Android 35 (API 35)
- **Min SDK**: Android 28 (API 28)  
- **Kotlin**: 2.0.20
- **Compose BOM**: 2024.09.01
- **Core Libraries**: AndroidX Activity Compose, Material3, Navigation, WorkManager

## Common Development Tasks

### Code Analysis (Working)
- View main activity: `cat app/src/main/java/com/unstable/confusion/MainActivity.kt`
- Check dependencies: `cat app/build.gradle.kts`
- Review manifest: `cat app/src/main/AndroidManifest.xml`

### Build Tasks (All Currently Failing)
**ALL gradle commands fail** until network restrictions are resolved. Commands that would work with network access:
- Clean: `gradle clean` -- **CURRENTLY FAILS**
- Debug build: `gradle assembleDebug` -- **CURRENTLY FAILS**
- Release build: `gradle assembleRelease` -- **CURRENTLY FAILS**  
- List tasks: `gradle tasks --all` -- **CURRENTLY FAILS**

### Code Style and Linting
**No linting tools configured** in current project. Standard Android development would use:
- `gradle lint` (when build works)
- Kotlin formatting would be handled by IDE

## Troubleshooting and Limitations

### Known Issues
1. **Build Failure**: Cannot download Android Gradle Plugin due to network restrictions
   - **Workaround**: None available in current environment
   - **Resolution Required**: Network access to download gradle plugins
   
2. **No Tests**: Project currently has no unit tests or instrumentation tests
   - **Impact**: Cannot validate code changes through automated testing
   - **Recommendation**: Add unit tests once build issues are resolved

3. **Minimal Functionality**: App is in early development with placeholder UI only
   - Current app just displays static text and non-functional button
   - No actual Stable Diffusion functionality implemented yet

### CI/CD Pipeline
The GitHub Actions workflow (`.github/workflows/android.yml`) will also fail with the same plugin download issue. It attempts to:
- Set up JDK 17
- Install Android SDK components  
- Run `gradle assembleDebug`
- Upload debug APK as artifact

## Quick Reference Commands

### Environment Check
```bash
java --version                    # Verify JDK 17+
echo $ANDROID_SDK_ROOT           # Check SDK path
ls $ANDROID_SDK_ROOT/platforms/  # List installed platforms
gradle --version                 # Verify gradle installation
```

### File Navigation  
```bash
# View main source files (currently only MainActivity.kt with 41 lines)
find . -name "*.kt" -exec echo "=== {} ===" \; -exec cat {} \;

# Check build configurations
cat build.gradle.kts                    # Root project plugins
cat app/build.gradle.kts               # App module config with dependencies  
cat settings.gradle.kts                # Project settings

# View Android resources
cat app/src/main/AndroidManifest.xml    # App manifest (minimal configuration)
cat app/src/main/res/values/strings.xml # String resources (just app name)

# Quick project analysis
find . -name "*.kt" | wc -l             # Count Kotlin files (currently 1)
find . -name "*.xml" | head -5          # List XML files
tree app/src/ 2>/dev/null || find app/src/ -type f  # Show source structure
```

### Project Overview Commands Output
```bash
# ls -la [repo-root]
.github/
.gitignore  
app/
build.gradle.kts
settings.gradle.kts

# Package structure
com.unstable.confusion.MainActivity.kt (Single activity with Compose UI)
```

## Development Recommendations

**Current Environment Limitations**: Since ALL gradle commands fail, focus on:

1. **Code Analysis and Modification**: 
   - Read and modify `MainActivity.kt` for UI changes
   - Update build configurations in `*.gradle.kts` files (cannot test until network issues resolved)
   - Analyze dependencies and project structure through file examination

2. **When Network/Build Issues are Resolved**: 
   - Always run `gradle assembleDebug` after making changes (will take ~2-3 minutes)
   - Test debug APK installation and basic functionality
   - Verify the main screen displays correctly

3. **Code Patterns for Current Codebase**:
   - Use Jetpack Compose patterns for UI (already established)
   - Follow Material3 design system (already imported)
   - Add new Compose functions in MainActivity.kt or create new files in same package

4. **Future Development Tasks**:
   - Add unit tests in `app/src/test/java/com/unstable/confusion/`
   - Add UI tests in `app/src/androidTest/java/com/unstable/confusion/`
   - Add new dependencies in `app/build.gradle.kts` dependencies block
   - Create additional activities or fragments as needed

5. **Immediate Actionable Tasks** (no build required):
   - Modify UI text in MainActivity.kt `App()` function
   - Add new Compose components and layouts
   - Update app version in `app/build.gradle.kts` (versionName/versionCode)
   - Modify string resources in `strings.xml`
   - Update app manifest permissions or configuration

**Critical Reminder**: You cannot validate any changes through building/testing until network restrictions preventing gradle plugin downloads are resolved. Focus on code analysis, modification, and preparation for when builds become possible.