version: 2
project_name: {{ .PB.GoPackageName }}
env:
    - GO111MODULE=on
    - CGO_ENABLED=0

builds:
    -   id: {{ .PB.GoPackageName }}
        binary: {{ .PB.GoPackageName }}
        main: ./cmd/
        tags:
            - release
        ldflags:
            - -s -w
            - --extldflags "-static -fpic"
            - -X github.com/lazygophers/utils/app.Name={{ Self ".ProjectName" }}
            - -X github.com/lazygophers/utils/app.Commit={{ Self ".FullCommit" }}
            - -X github.com/lazygophers/utils/app.ShortCommit={{ Self ".ShortCommit" }}
            - -X github.com/lazygophers/utils/app.Branch={{ Self ".Branch" }}
            - -X github.com/lazygophers/utils/app.Version={{ Self ".Version" }}
            - -X github.com/lazygophers/utils/app.Tag={{ Self ".Tag" }}
            - -X github.com/lazygophers/utils/app.BuildDate={{ Self ".Date" }}
            - -X github.com/lazygophers/utils/app.GoVersion={{ Self ".GOVERSION" }}
            - -X github.com/lazygophers/utils/app.GoOS={{ Self ".Os" }}
            - -X github.com/lazygophers/utils/app.Goarch={{ Self ".Arch" }}
            - -X github.com/lazygophers/utils/app.Goarm={{ Self ".Arm" }}
            - -X github.com/lazygophers/utils/app.Goamd64={{ Self ".Amd64" }}
            - -X github.com/lazygophers/utils/app.Gomips={{ Self ".Mips" }}
        gcflags:
            - -N -l
        targets:
            - linux_amd64_v3

archives:
    -   format: binary

report_sizes: true
