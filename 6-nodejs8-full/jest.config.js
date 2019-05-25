module.exports = {
    testEnvironment: 'node',
    moduleFileExtensions: [
        'ts',
        'tsx',
        'js'
    ],
    transform: {
        '^.+\\.(ts|tsx)$': 'ts-jest'
    },
    testMatch: [
        '**/?(*.)(spec|test).(ts|tsx|js)?(x)'
    ]
};