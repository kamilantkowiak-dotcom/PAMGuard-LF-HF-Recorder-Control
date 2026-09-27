# PAMGuard-LF-HF-Recorder-Control
A PAMGuard plugin for simultaneous manual control of LF and HF Sound Recorders.
# PAMGuard LF + HF Recorder Control

A small PAMGuard plugin providing simple manual control of the **LF and HF Sound Recorders** with two large buttons.

## Why this plugin?

When working with PAMGuard offshore, I wanted a simple way to manually start and stop the LF and HF Sound Recorders at the same time.

This plugin provides:

* **REC – LF + HF** — starts both Sound Recorders
* **STOP – LF + HF** — stops both Sound Recorders
* Manual operation only
* No automatic recording
* No `RecorderTrigger`

The plugin communicates with the existing PAMGuard Sound Recorder modules using their manual start/stop commands.

## Compatibility

Developed and tested with:

* **PAMGuard 2.02.17**

Testing with newer PAMGuard versions is welcome, particularly **2.02.18 and 2.02.19**.

If you test the plugin with another PAMGuard version, please report the version and whether both LF and HF recorders start and stop correctly.

## Installation

No Java, Maven or development environment is required to use the compiled plugin.

### 1. Download the compiled JAR

Download the latest `.jar` file from the **Releases** section of this GitHub repository.

### 2. Close PAMGuard

PAMGuard should be completely closed before installing the plugin.

### 3. Copy the JAR

Copy the downloaded file:

```text
lf-hf-recorder-control-1.0.0.jar
```

into the PAMGuard `plugins` folder.

For a standard Windows installation this will normally be:

```text
C:\Program Files\PAMGuard\plugins
```

If your PAMGuard installation is in a different location, copy the JAR into the `plugins` folder inside that installation.

### 4. Start PAMGuard

Start PAMGuard again.

The plugin should then be available as:

**LF + HF Recorder Control**

Add the module from the PAMGuard module menu.

## Using the plugin

The plugin provides two large buttons in the PAMGuard side panel:

**REC – LF + HF**

Starts the LF and HF Sound Recorders manually.

**STOP – LF + HF**

Stops the LF and HF Sound Recorders manually.

The plugin looks for the existing PAMGuard Sound Recorder modules and identifies the LF and HF recorders by their module names.

## Important

The plugin does **not** create or replace the PAMGuard Sound Recorders.

It only provides a simple interface for sending the existing manual `start` and `stop` commands to the LF and HF Sound Recorder modules.

The plugin does not use `RecorderTrigger` and does not introduce automatic recording logic.

## Source code

The complete source code is available in this repository.

The project can be compiled with Maven using the PAMGuard JAR corresponding to the target PAMGuard version.

## Development

The current development environment used for this project:

* Java 21
* Apache Maven 3.9.x
* PAMGuard 2.02.17

The PAMGuard JAR is required as a compile-time dependency but is **not included in this repository**.

## Feedback and testing

If you use this plugin, feedback is welcome.

In particular, I would appreciate reports from users testing:

* newer PAMGuard versions
* different PAMGuard installations
* different LF/HF recorder configurations

When reporting an issue, please include the PAMGuard version and a description of the behaviour observed.

---

**Project created for practical offshore PAM use.**
