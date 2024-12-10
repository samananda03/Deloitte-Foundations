function LicenseEligibility(age) {
    try {
        if (age < 16) {
            throw "Not eligible for any license. Minimum age is 16.";
        } 
        if (age === 16 || age === 17) {
            throw "Eligible for a gearless driving license.";
        }
        console.log("Eligible for a normal driving license.");
    } catch (error) {
        console.log("Exception:", error);
    }
}
LicenseEligibility(12);
LicenseEligibility(16);
LicenseEligibility(17);
LicenseEligibility(21);