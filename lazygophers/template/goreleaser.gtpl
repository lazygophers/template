project_name: {{ .PB.GoPackageName }}
env:
    - GO111MODULE=on
    - GOSUMDB=off
    - CGO_ENABLED=0

gomod:
    proxy: true
    mod: mod

builds:
    -   id: {{ .PB.GoPackageName }}
        binary: {{ .PB.GoPackageName }}
        main: ./cmd/
        tags:
            - release
        ldflags:
            - -s -w
            - --extldflags "-static -fpic"
            - -X github.com/lazygophers/utils/app.Name={{ .ProjectName }}
            - -X github.com/lazygophers/utils/app.Commit={{ .FullCommit }}
            - -X github.com/lazygophers/utils/app.ShortCommit={{ .ShortCommit }}
            - -X github.com/lazygophers/utils/app.Branch={{ .Branch }}
            - -X github.com/lazygophers/utils/app.Version={{ .Version }}
            - -X github.com/lazygophers/utils/app.Tag={{ .Tag }}
            - -X github.com/lazygophers/utils/app.BuildDate={{ .Date }}
            - -X github.com/lazygophers/utils/app.GoVersion={{ .Env.GOVERSION }}
            - -X github.com/lazygophers/utils/app.GoOS={{ .Os }}
            - -X github.com/lazygophers/utils/app.Goarch={{ .Arch }}
            - -X github.com/lazygophers/utils/app.Goarm={{ .Arm }}
            - -X github.com/lazygophers/utils/app.Goamd64={{ .Amd64 }}
            - -X github.com/lazygophers/utils/app.Gomips={{ .Mips }}
        gcflags:
            - -N -l
        targets:
            - linux_amd64_v3

archives:
    -   format: binary

report_sizes: true
