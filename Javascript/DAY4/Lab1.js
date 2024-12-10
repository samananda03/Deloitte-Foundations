const calculateResult = () => {
    const name = document.getElementById("name").value.trim();
    const program = document.getElementById("program").value.trim();
    const semester = document.getElementById("semester").value.trim();
    const pythonMarks = parseInt(document.getElementById("pythonMarks").value);
    const htmlMarks = parseInt(document.getElementById("htmlMarks").value);
    const cssMarks = parseInt(document.getElementById("cssMarks").value);

    if (isNaN(pythonMarks) || pythonMarks > 100 || pythonMarks < 0) {
        alert("Please enter valid Python marks (0-100).");
        return;
    }
    if (isNaN(htmlMarks) || htmlMarks > 100 || htmlMarks < 0) {
        alert("Please enter valid HTML marks (0-100).");
        return;
    }
    if (isNaN(cssMarks) || cssMarks > 100 || cssMarks < 0) {
        alert("Please enter valid CSS marks (0-100).");
        return;
    }

    const totalMarks = pythonMarks + htmlMarks + cssMarks;
    const percentage = (totalMarks / 300) * 100;
    let grade = "Fail";

    if (percentage >= 90) {
        grade = "A";
    } else if (percentage >= 80) {
        grade = "B";
    }

    const outputElement = document.getElementById("output");
    outputElement.innerHTML = `
        <strong>Student Result:</strong><br>
        Name: ${name}<br>
        Program Number: ${program}<br>
        Semester: ${semester}<br>
        Total Marks: ${totalMarks} / 300<br>
        Percentage: ${percentage.toFixed(2)}%<br>
        Grade: ${grade}
    `;
};
