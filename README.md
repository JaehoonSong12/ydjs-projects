<!-- 
 @requires
 1. VSCode extension: Markdown Preview Enhanced
 2. Shortcut: 'Ctrl' + 'Shift' + 'V'
 3. Split: Drag to right (->)

 @requires
 1. VSCode extension: Markdown All in One
 2. `File` > `Preferences` > `Keyboard Shortcuts`
 3. toggle code span > `Ctrl + '`
 4. toggle code block > `Ctrl + Shift + '`

 @usage
 1. End of Proof (Q.E.D.): <div style="text-align: right;">&#11035;</div>
 2. End of Each Section: 

     <br /><br /><br />

     ---



     <p align="right">(<a href="#readme-top">back to top</a>)</p>
     

 3. ![image_title_](images/imagefile.png)
 4. [url_title](URL)
 -->
<!-- Anchor Tag (Object) for "back to top" -->
<a id="readme-top"></a>

# YDJS Projects Repository

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![GitHub stars](https://img.shields.io/github/stars/JaehoonSong12/ydjs-projects)](https://github.com/JaehoonSong12/ydjs-projects/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/JaehoonSong12/ydjs-projects)](https://github.com/JaehoonSong12/ydjs-projects/network)

A comprehensive collection of student projects and educational resources for computer science education, featuring projects from AP Computer Science Principles, GT CS1301, and GT CS1331 courses.

## Table of Contents

- [About](#about)
- [Projects](#projects)
- [Getting Started](#getting-started)
- [Student Work](#student-work)
- [Contributing](#contributing)
- [Contact](#contact)
- [License](#license)

## About

This repository serves as a central hub for educational projects and student work in computer science. It contains:

- **AP Computer Science Principles** projects (JavaScript)
- **GT CS1301: Introduction to Computing** projects (Python)
- **GT CS1331: Object-Oriented Programming** projects (Java)
- Student showcase applications and demonstrations

## Projects

### Current Course Projects

| Course | Language | Description |
|--------|----------|-------------|
| AP CS Principles | JavaScript | Web development and computational thinking projects |
| GT CS1301 | Python | Introduction to programming concepts and problem solving |
| GT CS1331 | Java | Object-oriented programming and software design |

### Featured Student Work

Browse through student projects and applications in the respective course directories. Each project includes:
- Source code and documentation
- Demo applications
- Project descriptions and learning objectives

## Getting Started

### Prerequisites

- Git installed on your system
- Appropriate development environment for your chosen language:
  - **JavaScript**: Node.js and a modern web browser
  - **Python**: Python 3.7+ and pip
  - **Java**: JDK 11+ and an IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/JaehoonSong12/ydjs-projects.git
cd ydjs-projects
```

2. Navigate to your specific project directory:
```bash
# For AP CS Principles projects
cd ap-cs-principles/

# For GT CS1301 projects  
cd gt-cs1301/

# For GT CS1331 projects
cd gt-cs1331/
```

### For Students

#### Using Git Scripts (Recommended)

Students are provided with automated git scripts for version control:

1. **Initialize your project:**
   ```bash
   ./git00-init
   cd <your-project-directory>
   ```

2. **Before starting work (pull latest changes):**
   ```bash
   ../git01-pull.sh
   ```

3. **After completing work (push changes):**
   ```bash
   ../git03-branch+push.sh <branch-name> [commit-message]
   ```

#### Manual Git Workflow

If you prefer manual git operations:

```bash
# Clone the repository
git clone https://github.com/JaehoonSong12/ydjs-projects.git
cd ydjs-projects

# Create and switch to your feature branch
git checkout -b your-feature-branch

# Make your changes, then commit and push
git add .
git commit -m "Your commit message"
git push origin your-feature-branch
```

## Student Work

This repository showcases student projects and applications. Students can:

- Submit their projects through pull requests
- View and learn from other students' work
- Demonstrate their applications and code
- Collaborate on shared learning objectives

### How to Submit Your Work

1. Fork this repository
2. Create a feature branch for your project
3. Add your project files and documentation
4. Submit a pull request with a clear description

## Contributing

We welcome contributions from students, educators, and the community.

### For Students
- Submit your completed projects
- Improve existing documentation
- Add helpful comments and explanations

### For Educators
- Suggest curriculum improvements
- Add new project templates
- Share best practices

### Contribution Guidelines

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Contact

**Instructor:** Jaehoon Song  
**Email:** manual20151276@gmail.com  
**GitHub:** [@JaehoonSong12](https://github.com/JaehoonSong12)

For technical support or to request git scripts, please reach out to the instructor.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

---

<p align="right">(<a href="#readme-top">back to top</a>)</p>

