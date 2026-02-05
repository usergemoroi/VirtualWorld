# Contributing to VirtualWorld

First off, thank you for considering contributing to VirtualWorld! It's people like you that make VirtualWorld such a great tool.

## Code of Conduct

This project and everyone participating in it is governed by our Code of Conduct. By participating, you are expected to uphold this code.

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check the existing issues as you might find out that you don't need to create one. When you are creating a bug report, please include as many details as possible:

* **Use a clear and descriptive title**
* **Describe the exact steps which reproduce the problem**
* **Provide specific examples to demonstrate the steps**
* **Describe the behavior you observed after following the steps**
* **Explain which behavior you expected to see instead and why**
* **Include screenshots and animated GIFs** if possible
* **Include your environment details** (Android version, device model, app version)

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, please include:

* **Use a clear and descriptive title**
* **Provide a step-by-step description of the suggested enhancement**
* **Provide specific examples to demonstrate the steps**
* **Describe the current behavior** and **explain which behavior you expected to see instead**
* **Explain why this enhancement would be useful**

### Pull Requests

* Fill in the required template
* Do not include issue numbers in the PR title
* Follow the Kotlin style guide
* Include thoughtfully-worded, well-structured tests
* Document new code
* End all files with a newline

## Development Process

1. **Fork the repo** and create your branch from `main`
2. **Make your changes** following our coding standards
3. **Add tests** if you've added code that should be tested
4. **Ensure the test suite passes**
5. **Make sure your code lints**
6. **Issue a pull request**

## Coding Standards

### Kotlin Style Guide

* Follow the [official Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
* Use meaningful variable and function names
* Keep functions small and focused on a single task
* Use Kotlin idioms (data classes, sealed classes, extension functions, etc.)
* Prefer immutability

### Architecture Guidelines

* Follow MVVM pattern
* Use Repository pattern for data access
* Keep ViewModels free of Android framework dependencies
* Use dependency injection (Hilt)
* Separate UI logic from business logic

### Git Commit Messages

* Use the present tense ("Add feature" not "Added feature")
* Use the imperative mood ("Move cursor to..." not "Moves cursor to...")
* Limit the first line to 72 characters or less
* Reference issues and pull requests liberally after the first line
* Consider starting the commit message with an applicable emoji:
    * 🎨 `:art:` when improving the format/structure of the code
    * 🐎 `:racehorse:` when improving performance
    * 📝 `:memo:` when writing docs
    * 🐛 `:bug:` when fixing a bug
    * 🔥 `:fire:` when removing code or files
    * ✅ `:white_check_mark:` when adding tests
    * 🔒 `:lock:` when dealing with security
    * ⬆️ `:arrow_up:` when upgrading dependencies
    * ⬇️ `:arrow_down:` when downgrading dependencies

### Testing

* Write unit tests for business logic
* Write integration tests for repositories
* Write UI tests for critical user flows
* Aim for high test coverage (>80%)
* Mock external dependencies

### Documentation

* Update README.md if needed
* Add KDoc comments for public APIs
* Include code examples in documentation
* Keep ARCHITECTURE.md up to date

## Project Structure

Familiarize yourself with the project structure:

```
app/
├── data/           # Data layer (API, Database, Repositories)
├── di/             # Dependency Injection
├── presentation/   # UI layer (Screens, ViewModels, Components)
├── util/           # Utility classes
└── ...
```

## Setting Up Development Environment

1. Install Android Studio (latest stable version)
2. Clone the repository
3. Open the project in Android Studio
4. Let Gradle sync
5. Create a Firebase project and add `google-services.json`
6. Run the app on an emulator or device

## Questions?

Feel free to open an issue with your question or contact the maintainers directly.

## Recognition

Contributors will be recognized in our README.md file.

Thank you for contributing to VirtualWorld! 🎉
